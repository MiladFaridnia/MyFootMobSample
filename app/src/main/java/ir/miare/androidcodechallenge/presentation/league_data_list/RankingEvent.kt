package ir.miare.androidcodechallenge.presentation.league_data_list

import ir.miare.androidcodechallenge.data.model.League
import ir.miare.androidcodechallenge.data.model.Player

sealed class RankingEvent {
    data class SortChanged(val sort: RankingSort) : RankingEvent()
    data class PlayerClicked(val player: Player) : RankingEvent()
    object SheetDismissed : RankingEvent()
    data class FollowClicked(val player: Player, val league: League) : RankingEvent()
    object Retry : RankingEvent()
}
