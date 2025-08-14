package ir.miare.androidcodechallenge.data.model

import com.fasterxml.jackson.annotation.JsonProperty

data class League(
    @JsonProperty("name") val name: String,
    @JsonProperty("country") val country: String,
    @JsonProperty("rank") val rank: Int,
    @JsonProperty("total_matches") val totalMatches: Int,
) {
    companion object {
        val sampleData = League(
            name = "Sample League Name",
            country = "Sample Country",
            rank = 1,
            totalMatches = 100,
        )
    }
}
