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
class IsPlayerFollowedUseCaseTest {

    private lateinit var repository: FakeFollowedPlayerRepository
    private lateinit var useCase: IsPlayerFollowedUseCase
    private val messi = Player("Messi", Team("PSG", 1), 30, false)
    private val league = League("Ligue 1", "France", 1, 38)

    @Before
    fun setup() {
        repository = FakeFollowedPlayerRepository()
        useCase = IsPlayerFollowedUseCase(repository)
    }

    @Test
    fun `returns true when player is followed`() = runTest {
        repository.follow(messi, league)
        assert(useCase("Messi"))
    }

    @Test
    fun `returns false when player is not followed`() = runTest {
        assert(useCase("Messi").not())
    }
}
