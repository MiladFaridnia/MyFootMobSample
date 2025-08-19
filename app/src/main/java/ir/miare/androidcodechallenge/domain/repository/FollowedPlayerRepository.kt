package ir.miare.androidcodechallenge.domain.repository

import ir.miare.androidcodechallenge.data.db.FollowedPlayerEntity
import ir.miare.androidcodechallenge.data.model.League
import ir.miare.androidcodechallenge.data.model.Player
import kotlinx.coroutines.flow.Flow

interface FollowedPlayerRepository {
    fun getFollowedPlayers(): Flow<List<FollowedPlayerEntity>>
    suspend fun follow(player: Player, league: League)
    suspend fun unfollow(playerName: String)
    suspend fun isFollowed(playerName: String): Boolean
}
