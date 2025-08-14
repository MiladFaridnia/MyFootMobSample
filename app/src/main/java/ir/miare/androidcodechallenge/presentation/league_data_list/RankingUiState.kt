package ir.miare.androidcodechallenge.presentation.league_data_list

import androidx.paging.compose.LazyPagingItems
import ir.miare.androidcodechallenge.data.model.LeagueData
import ir.miare.androidcodechallenge.data.model.Player

data class RankingUiState(
    val sort: RankingSort = RankingSort.None,
    val leagues: LazyPagingItems<LeagueData>? = null,
    val selectedPlayer: Player? = null,
    val showSheet: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
