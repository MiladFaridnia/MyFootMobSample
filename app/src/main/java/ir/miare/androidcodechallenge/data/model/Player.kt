package ir.miare.androidcodechallenge.data.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
data class Player(
    @JsonProperty("name") val name: String,
    @JsonProperty("team") val team: Team,
    @JsonProperty("total_goal") val totalGoal: Int,
    @JsonIgnore var isFollowed: Boolean = false
) : Serializable {
    companion object {
        val sampleData = Player(
            name = "Sample Player Name",
            team = Team.sampleData,
            isFollowed = true,
            totalGoal = 34
        )

    }

}