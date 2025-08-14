package ir.miare.androidcodechallenge.data.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class LeagueData(
    @JsonProperty("league") var league: League,
    @JsonProperty("players") var players: List<Player>
) {
    companion object {
        val sampleData = LeagueData(
            league = League.sampleData,
            players = Player.sampleListData
        )
        val sampleListData = listOf(
            sampleData.copy(league = League.sampleData.copy(name = "League 1")),
            sampleData.copy(league = League.sampleData.copy(name = "League 2")),
        )
    }
}