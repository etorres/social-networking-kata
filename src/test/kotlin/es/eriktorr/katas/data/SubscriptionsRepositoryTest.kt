package es.eriktorr.katas.data

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SubscriptionsRepositoryTest {

    companion object {
        private val ALICE = "Alice"
        private val BOB = "Bob"
        private val CHARLIE = "Charlie"
    }

    private val subscriptionsRepository = SubscriptionsRepository()

    @Test
    fun `a user can subscribe to other's users time-lines`() {
        subscriptionsRepository.subscribe(userName = CHARLIE, followedUserName = ALICE)
        subscriptionsRepository.subscribe(userName = CHARLIE, followedUserName = BOB)

        assertThat(subscriptionsRepository.subscriptionsOf(CHARLIE))
                .hasSize(2)
                .containsOnly(ALICE, BOB)
    }

}