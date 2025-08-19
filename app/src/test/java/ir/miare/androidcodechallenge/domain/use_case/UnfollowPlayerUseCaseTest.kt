package ir.miare.androidcodechallenge.domain.use_case

import ir.miare.androidcodechallenge.data.FakeFollowedPlayerRepository
import ir.miare.androidcodechallenge.data.model.League
import ir.miare.androidcodechallenge.data.model.Player
import ir.miare.androidcodechallenge.data.model.Team
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UnfollowPlayerUseCaseTest {

    private lateinit var repository: FakeFollowedPlayerRepository
    private lateinit var useCase: UnfollowPlayerUseCase
    private val messi = Player("Messi", Team("PSG", 1), 30, false)
    private val league = League("Ligue 1", "France", 1, 38)

    @Before
    fun setup() {
        repository = FakeFollowedPlayerRepository()
        useCase = UnfollowPlayerUseCase(repository)
    }

    @Test
    fun `unfollow removes player from repository`() = runTest {
        repository.follow(messi, league)
        assert(repository.isFollowed("Messi"))

        useCase("Messi")
        assert(!repository.isFollowed("Messi"))
    }
}
