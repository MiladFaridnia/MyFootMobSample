package ir.miare.androidcodechallenge.data.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class Team(
    @JsonProperty("name") val name: String,
    @JsonProperty("rank") val rank: Int
) : Serializable {
    companion object {
        val sampleData = Team(
            name = "Sample Team Name",
            rank = 1
        )
    }
}
