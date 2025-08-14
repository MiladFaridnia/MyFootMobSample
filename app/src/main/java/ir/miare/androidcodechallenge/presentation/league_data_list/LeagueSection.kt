package ir.miare.androidcodechallenge.presentation.league_data_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ir.miare.androidcodechallenge.data.model.League
import ir.miare.androidcodechallenge.data.model.LeagueData
import ir.miare.androidcodechallenge.data.model.Player
import ir.miare.androidcodechallenge.presentation.util.LightAndDarkPreview
import ir.miare.androidcodechallenge.presentation.util.MyFootMobTheme

@Composable
fun LeagueSection(
    leagueData: LeagueData, onFollowClick: (Player) -> Unit, onPlayerClick: (Player) -> Unit
) {
    Column {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            text = "${leagueData.league.name} (${leagueData.league.country})",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(4.dp))
        leagueData.players.forEach { player ->
            PlayerItem(
                player = player, onFollowClick = onFollowClick, onClick = { onPlayerClick(player) })
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@LightAndDarkPreview
@Composable
fun PreviewLeagueSection() {
    MyFootMobTheme {
        LeagueSection(
            leagueData = LeagueData(
                league = League.sampleData, players = listOf(
                    Player.sampleData,
                    Player.sampleData.copy(name = "Player 2"),
                    Player.sampleData.copy(name = "Player 3"),
                    Player.sampleData.copy(name = "Player 4"),
                )
            ), onFollowClick = {}, onPlayerClick = {})
    }
}