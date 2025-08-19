package ir.miare.androidcodechallenge.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import ir.miare.androidcodechallenge.data.model.LeagueData
import ir.miare.androidcodechallenge.domain.repository.LeagueRepository
import ir.miare.androidcodechallenge.presentation.league_data_list.RankingSort

class FakeLeagueRepository(
    private val leagues: List<LeagueData>
) : LeagueRepository {

    override fun getLeaguePager(pageSize: Int, sort: RankingSort): Pager<Int, LeagueData> {
        return Pager(
            config = PagingConfig(pageSize = pageSize, enablePlaceholders = false),
            pagingSourceFactory = {
                object : PagingSource<Int, LeagueData>() {
                    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LeagueData> {
                        val sortedLeagues = when (sort) {
                            RankingSort.None -> leagues
                            RankingSort.ByTeam -> leagues.map { it.copy(players = it.players.sortedBy { p -> p.team.name }) }
                            RankingSort.ByLeagueRanking -> leagues.sortedBy { it.league.rank }
                            RankingSort.ByMostGoals -> leagues.map { it.copy(players = it.players.sortedByDescending { p -> p.totalGoal }) }
                            RankingSort.ByAverageGoalsPerMatch -> leagues.sortedByDescending { l ->
                                val totalGoals = l.players.sumOf { it.totalGoal }
                                if (l.league.totalMatches > 0) totalGoals.toDouble() / l.league.totalMatches else 0.0
                            }
                        }

                        val page = params.key ?: 0
                        val fromIndex = page * pageSize
                        val toIndex = minOf(fromIndex + pageSize, sortedLeagues.size)
                        val data = sortedLeagues.subList(fromIndex, toIndex)

                        return LoadResult.Page(
                            data = data,
                            prevKey = if (page == 0) null else page - 1,
                            nextKey = if (toIndex < sortedLeagues.size) page + 1 else null
                        )
                    }

                    override fun getRefreshKey(state: PagingState<Int, LeagueData>): Int? = null
                }
            }
        )
    }
}
