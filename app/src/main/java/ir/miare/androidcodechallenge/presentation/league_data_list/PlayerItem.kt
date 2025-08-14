package ir.miare.androidcodechallenge.presentation.league_data_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ir.miare.androidcodechallenge.R
import ir.miare.androidcodechallenge.data.model.Player
import ir.miare.androidcodechallenge.presentation.util.LightAndDarkPreview
import ir.miare.androidcodechallenge.presentation.util.MyFootMobTheme

@Composable
fun PlayerItem(
    player: Player,
    onFollowClick: (Player) -> Unit,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                shape = MaterialTheme.shapes.medium
            )
            .background(MaterialTheme.colorScheme.tertiary)
            .clickable { onClick() }
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start
    ) {
        Row {
            Text(
                text = player.name,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                modifier = Modifier.clickable { onFollowClick(player) },
                painter = painterResource(
                    if (player.isFollowed)
                        R.drawable.ic_follow
                    else
                        R.drawable.ic_unfollow
                ),
                contentDescription = "Follow",
                tint = if (player.isFollowed)
                    MaterialTheme.colorScheme.error
                else
                    MaterialTheme.colorScheme.onSurface,
            )
        }
        Spacer(modifier = Modifier.padding(4.dp))
        Row {
            Text(
                text = "Team: ${player.team.name}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Goals: ${player.totalGoal}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
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

