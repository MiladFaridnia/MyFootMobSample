package ir.miare.androidcodechallenge.data

import ir.miare.androidcodechallenge.data.db.FollowedPlayerEntity
import ir.miare.androidcodechallenge.data.model.League
import ir.miare.androidcodechallenge.data.model.Player
import ir.miare.androidcodechallenge.domain.repository.FollowedPlayerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow

class FakeFollowedPlayerRepository : FollowedPlayerRepository {
    private val _players = mutableListOf<FollowedPlayerEntity>()
    private val _playersFlow = MutableStateFlow<List<FollowedPlayerEntity>>(emptyList())

    override fun getFollowedPlayers(): Flow<List<FollowedPlayerEntity>> = _playersFlow

    override suspend fun follow(player: Player, league: League) {
        val entity = FollowedPlayerEntity(
            playerName = player.name,
            totalGoal = player.totalGoal,
            teamName = player.team.name,
            teamRank = player.team.rank,
            leagueName = league.name,
            leagueCountry = league.country,
            leagueRank = league.rank,
            leagueTotalMatches = league.totalMatches
        )
        _players.add(entity)
        _playersFlow.value = _players.toList() // update synchronously
    }

    override suspend fun unfollow(playerName: String) {
        _players.removeAll { it.playerName == playerName }
        _playersFlow.value = _players.toList() // update synchronously
    }

    override suspend fun isFollowed(playerName: String): Boolean {
        return _players.any { it.playerName == playerName } // reads _players directly
    }
}
