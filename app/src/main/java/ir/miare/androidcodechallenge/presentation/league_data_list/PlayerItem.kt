package ir.miare.androidcodechallenge.presentation.league_data_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ir.miare.androidcodechallenge.data.model.Player
import ir.miare.androidcodechallenge.presentation.util.LightAndDarkPreview
import ir.miare.androidcodechallenge.presentation.util.MyFootMobTheme

@Composable
fun PlayerItem(
    player: Player,
    onFollowClick: (Player) -> Unit,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(player.name, style = MaterialTheme.typography.bodyLarge)
            Text(
                text = "Team: ${player.team.name} • Goals: ${player.totalGoal}",
                style = MaterialTheme.typography.bodySmall,
            )
        }
        Button(onClick = { onFollowClick(player) }) {
            Text(if (player.isFollowed) "Unfollow" else "Follow")
        }
    }
}

@LightAndDarkPreview
@Composable
fun PreviewPlayerItem() {
    MyFootMobTheme {
        PlayerItem(
            player = Player.sampleData,
            onFollowClick = {},
            onClick = {}
        )
    }
}

