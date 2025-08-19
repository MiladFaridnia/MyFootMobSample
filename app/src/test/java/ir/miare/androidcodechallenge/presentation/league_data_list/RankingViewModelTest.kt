package ir.miare.androidcodechallenge.presentation.league_data_list

import ir.miare.androidcodechallenge.MainDispatcherRule
import ir.miare.androidcodechallenge.data.FakeFollowedPlayerRepository
import ir.miare.androidcodechallenge.data.FakeLeagueRepository
import ir.miare.androidcodechallenge.data.model.League
import ir.miare.androidcodechallenge.data.model.LeagueData
import ir.miare.androidcodechallenge.data.model.Player
import ir.miare.androidcodechallenge.data.model.Team
import ir.miare.androidcodechallenge.domain.use_case.FollowPlayerUseCase
import ir.miare.androidcodechallenge.domain.use_case.GetFollowedPlayersUseCase
import ir.miare.androidcodechallenge.domain.use_case.GetLeagueDataUseCase
import ir.miare.androidcodechallenge.domain.use_case.IsPlayerFollowedUseCase
import ir.miare.androidcodechallenge.domain.use_case.UnfollowPlayerUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RankingViewModelTest {

    private lateinit var followedRepo: FakeFollowedPlayerRepository
    private lateinit var leagueRepo: FakeLeagueRepository
    private lateinit var viewModel: RankingViewModel

    private val messi = Player("Messi", Team("PSG", 1), 30, false)
    private val league = League("Ligue 1", "France", 1, 38)

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule() // sets Dispatchers.Main to TestDispatcher

    @Before
    fun setup() {
        followedRepo = FakeFollowedPlayerRepository() // fresh repo
        leagueRepo = FakeLeagueRepository(
            leagues = listOf(
                LeagueData(
                    league = league,
                    players = listOf(messi)
                )
            )
        )

        viewModel = RankingViewModel(
            getLeagueDataUseCase = GetLeagueDataUseCase(leagueRepo),
            getFollowedPlayersUseCase = GetFollowedPlayersUseCase(followedRepo),
            isPlayerFollowedUseCase = IsPlayerFollowedUseCase(followedRepo),
            followPlayerUseCase = FollowPlayerUseCase(followedRepo),
            unfollowPlayerUseCase = UnfollowPlayerUseCase(followedRepo)
        )
    }

    @Test
    fun `FollowClicked adds player to repository`() = runTest {
        viewModel.onEvent(RankingEvent.FollowClicked(messi, league))
        advanceUntilIdle()
        assert(followedRepo.isFollowed(messi.name))
    }

    @Test
    fun `FollowClicked toggles player correctly`() = runTest {
        // prefill
        followedRepo.follow(messi, league)
        assert(followedRepo.isFollowed(messi.name))

        // first click → unfollow
        viewModel.onEvent(RankingEvent.FollowClicked(messi, league))
        advanceUntilIdle()
        assert(followedRepo.isFollowed(messi.name).not())

        // second click → follow
        viewModel.onEvent(RankingEvent.FollowClicked(messi, league))
        advanceUntilIdle()
        assert(followedRepo.isFollowed(messi.name))
    }

    @Test
    fun `SortChanged updates uiState`() = runTest {
        viewModel.onEvent(RankingEvent.SortChanged(RankingSort.ByMostGoals))
        assert(viewModel.uiState.value.sort == RankingSort.ByMostGoals)
    }

    @Test
    fun `PlayerClicked updates uiState with selected player`() = runTest {
        viewModel.onEvent(RankingEvent.PlayerClicked(messi))
        assert(viewModel.uiState.value.selectedPlayer == messi)
        assert(viewModel.uiState.value.showSheet)
    }

    @Test
    fun `SheetDismissed clears selected player`() = runTest {
        viewModel.onEvent(RankingEvent.PlayerClicked(messi))
        viewModel.onEvent(RankingEvent.SheetDismissed)
        assert(viewModel.uiState.value.selectedPlayer == null)
        assert(!viewModel.uiState.value.showSheet)
    }
}
