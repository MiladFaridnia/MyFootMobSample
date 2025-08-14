import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import ir.miare.androidcodechallenge.R
import ir.miare.androidcodechallenge.data.model.LeagueData
import ir.miare.androidcodechallenge.presentation.league_data_list.LeagueSection
import ir.miare.androidcodechallenge.presentation.league_data_list.RankingEvent
import ir.miare.androidcodechallenge.presentation.league_data_list.RankingSort
import ir.miare.androidcodechallenge.presentation.league_data_list.RankingUiState
import ir.miare.androidcodechallenge.presentation.league_data_list.RankingViewModel
import ir.miare.androidcodechallenge.presentation.player_detail.PlayerDetailsContent
import ir.miare.androidcodechallenge.presentation.util.LightAndDarkPreview
import ir.miare.androidcodechallenge.presentation.util.MyFootMobTheme
import kotlinx.coroutines.flow.flowOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RankingScreen(viewModel: RankingViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val leagues = viewModel.leaguesPagingFlow.collectAsLazyPagingItems()

    PlayerDetailBottomSheet(uiState = uiState, onEvent = viewModel::onEvent)

    Column(modifier = Modifier.fillMaxSize()) {
        RankingSort(uiState = uiState, onEvent = viewModel::onEvent)
        RankingList(leagues = leagues, onEvent = viewModel::onEvent)
    }
}

@Composable
private fun RankingList(
    leagues: LazyPagingItems<LeagueData>,
    onEvent: (RankingEvent) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(leagues.itemCount) { index ->
            leagues[index]?.let { leagueData ->
                LeagueSection(
                    leagueData = leagueData,
                    onFollowClick = { player ->
                        onEvent(RankingEvent.FollowClicked(player, leagueData.league))
                    },
                    onPlayerClick = { player ->
                        onEvent(RankingEvent.PlayerClicked(player))
                    }
                )
            }
        }

        leagues.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { //"Loading first page..."
                        CircularProgressIndicator()
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item {//"Loading more..."
                        CircularProgressIndicator()
                    }
                }

                loadState.append is LoadState.Error -> {
                    val e = leagues.loadState.append as LoadState.Error
                    item {
                        Column {
                            Text("Error: ${e.error.localizedMessage}")
                            Button(onClick = { retry() }) { Text("Retry") }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RankingSort(
    uiState: RankingUiState,
    onEvent: (RankingEvent) -> Unit
) {
    var showSortSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { showSortSheet = true }
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_sort),
            contentDescription = "Sort",
            tint = MaterialTheme.colorScheme.onSurface,
        )
        Text(
            text = "Sort by:",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .clickable { showSortSheet = true }
                .padding(8.dp)
        )
        Text(
            text = uiState.sort.displayName(),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .padding(8.dp)
        )
    }

    if (showSortSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSortSheet = false },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(Modifier.fillMaxWidth()) {
                    Text(
                        text = "Select Sort Type",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(Modifier.weight(1f))
                    Icon(
                        painter = painterResource(R.drawable.ic_sort),
                        contentDescription = "Sort",
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
                RankingSort.values().forEach { sortOption ->
                    Text(
                        text = sortOption.displayName(),
                        style = if (sortOption == uiState.sort)
                            MaterialTheme.typography.bodyLarge
                        else
                            MaterialTheme.typography.bodyMedium,
                        color = if (sortOption == uiState.sort)
                            MaterialTheme.colorScheme.error
                        else
                            MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                showSortSheet = false
                                onEvent(RankingEvent.SortChanged(sortOption))
                            }
                            .padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun PlayerDetailBottomSheet(
    uiState: RankingUiState,
    onEvent: (RankingEvent) -> Unit,
) {
    if (uiState.showSheet && uiState.selectedPlayer != null) {
        ModalBottomSheet(
            onDismissRequest = { onEvent(RankingEvent.SheetDismissed) },
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        ) {
            PlayerDetailsContent(player = uiState.selectedPlayer)
        }
    }
}

@LightAndDarkPreview
@Composable
fun PreviewRankingScreen() {
    MyFootMobTheme {
        val leagues = flowOf(
            PagingData.from(LeagueData.sampleListData)
        )
            .collectAsLazyPagingItems()

        Column(modifier = Modifier.fillMaxSize()) {
            RankingSort(uiState = RankingUiState(sort = RankingSort.None), onEvent = {})
            RankingList(leagues = leagues, onEvent = {})
        }
    }
}
