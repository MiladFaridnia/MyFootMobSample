package ir.miare.androidcodechallenge.presentation.league_data_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.miare.androidcodechallenge.data.db.FollowedPlayerEntity
import ir.miare.androidcodechallenge.data.model.LeagueData
import ir.miare.androidcodechallenge.domain.use_case.FollowPlayerUseCase
import ir.miare.androidcodechallenge.domain.use_case.GetFollowedPlayersUseCase
import ir.miare.androidcodechallenge.domain.use_case.GetLeagueDataUseCase
import ir.miare.androidcodechallenge.domain.use_case.IsPlayerFollowedUseCase
import ir.miare.androidcodechallenge.domain.use_case.UnfollowPlayerUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RankingViewModel @Inject constructor(
    private val getLeagueDataUseCase: GetLeagueDataUseCase,
    private val getFollowedPlayersUseCase: GetFollowedPlayersUseCase,
    private val isPlayerFollowedUseCase: IsPlayerFollowedUseCase,
    private val followPlayerUseCase: FollowPlayerUseCase,
    private val unfollowPlayerUseCase: UnfollowPlayerUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RankingUiState())
    val uiState: StateFlow<RankingUiState> = _uiState.asStateFlow()

    private val _sort = MutableStateFlow<RankingSort>(RankingSort.None)
    private val followedPlayersFlow: Flow<List<FollowedPlayerEntity>> =
        getFollowedPlayersUseCase()

    // Combined PagingData of leagues with players + follow status
    @OptIn(ExperimentalCoroutinesApi::class)
    val leaguesPagingFlow: Flow<PagingData<LeagueData>> =
        _sort.flatMapLatest { sort ->
            getLeagueDataUseCase(pageSize = 5, sort = sort)
        }
            .cachedIn(viewModelScope)
            .combine(followedPlayersFlow) { pagingData, followedEntities ->
                val followedSet = followedEntities.map { it.playerName }.toSet()
                pagingData.map { league ->
                    league.copy(
                        players = league.players.map { player ->
                            player.copy(isFollowed = player.name in followedSet)
                        }
                    )
                }
            }

    fun onEvent(event: RankingEvent) {
        when (event) {
            is RankingEvent.SortChanged -> {
                _sort.value = event.sort
                _uiState.update { it.copy(sort = event.sort) }
            }

            is RankingEvent.PlayerClicked -> {
                _uiState.update { it.copy(selectedPlayer = event.player, showSheet = true) }
            }

            is RankingEvent.SheetDismissed -> {
                _uiState.update { it.copy(showSheet = false, selectedPlayer = null) }
            }

            is RankingEvent.FollowClicked -> {
                viewModelScope.launch {
                    if (isPlayerFollowedUseCase(event.player.name)) {
                        unfollowPlayerUseCase(event.player.name)
                    } else {
                        followPlayerUseCase(event.player, event.league)
                    }
                }
            }

            RankingEvent.Retry -> {

            }
        }
    }
}
