package ir.miare.androidcodechallenge.data.repository

import ir.miare.androidcodechallenge.data.db.FollowedPlayerDao
import ir.miare.androidcodechallenge.data.db.FollowedPlayerEntity
import ir.miare.androidcodechallenge.data.model.League
import ir.miare.androidcodechallenge.data.model.Player
import ir.miare.androidcodechallenge.domain.repository.FollowedPlayerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FollowedPlayerRepositoryImpl @Inject constructor(
    private val dao: FollowedPlayerDao
) : FollowedPlayerRepository {

    override fun getFollowedPlayers(): Flow<List<FollowedPlayerEntity>> =
        dao.getFollowedPlayers()

    override suspend fun follow(player: Player, league: League) {
        dao.insert(
            FollowedPlayerEntity(
                playerName = player.name,
                totalGoal = player.totalGoal,
                teamName = player.team.name,
                teamRank = player.team.rank,
                leagueName = league.name,
                leagueCountry = league.country,
                leagueRank = league.rank,
                leagueTotalMatches = league.totalMatches
            )
        )
    }

    override suspend fun unfollow(playerName: String) {
        dao.deleteByName(playerName)
    }

    override suspend fun isFollowed(playerName: String): Boolean {
        return dao.isFollowed(playerName)
    }
}