package ir.miare.androidcodechallenge.presentation.player_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ir.miare.androidcodechallenge.data.model.Player
import ir.miare.androidcodechallenge.presentation.util.LightAndDarkPreview
import ir.miare.androidcodechallenge.presentation.util.MyFootMobTheme

@Composable
fun PlayerDetailsContent(player: Player) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = player.name,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Team: ${player.team.name}",
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = "Total Goals: ${player.totalGoal}",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { }) {
            Text(if (player.isFollowed) "Unfollow" else "Follow")
        }
    }
}


@LightAndDarkPreview
@Composable
fun PreviewPlayerDetailsContent() {
    MyFootMobTheme {
        PlayerDetailsContent(player = Player.sampleData)
    }
}



