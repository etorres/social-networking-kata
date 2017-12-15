package es.eriktorr.katas.acceptance

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.inOrder
import com.nhaarman.mockito_kotlin.mock
import es.eriktorr.katas.Clock
import es.eriktorr.katas.SocialNetworkingConsole
import es.eriktorr.katas.TimeLinePrinter
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class SocialNetworkingConsoleTest {

    companion object {
        private val ALICE = "Alice"
        private val BOB = "Bob"
        private val CHARLIE = "Charlie"
    }

    private val clock = mock<Clock>()

    private val timeLinePrinter = mock<TimeLinePrinter>()

    private val socialNetworkingConsole = SocialNetworkingConsole(clock, timeLinePrinter)

    /*
    a user can publish messages to a personal time-line
    a user can subscribe to other users time-lines, and view an aggregated list of all subscriptions
     */

    @Test
    fun `a user can publish messages to a personal time-line`() {
        given(clock.now())
                .willReturn(LocalDateTime.of(2017, 12, 15, 8, 30, 10))
                .willReturn(LocalDateTime.of(2017, 12, 15, 8, 31, 10))
                .willReturn(LocalDateTime.of(2017, 12, 15, 8, 32, 10))
                .willReturn(LocalDateTime.of(2017, 12, 15, 8, 35, 10))
                .willReturn(LocalDateTime.of(2017, 12, 15, 8, 33, 10))

        socialNetworkingConsole.submit("${ALICE} -> I love the weather today")
        socialNetworkingConsole.submit("${BOB} -> Damn! We lost!")
        socialNetworkingConsole.submit("${BOB} -> Good game though.")
        socialNetworkingConsole.submit(ALICE)
        socialNetworkingConsole.submit(BOB)

        socialNetworkingConsole.submit("${CHARLIE} -> I'm in New York today! Anyone wants to have a coffee?")
        socialNetworkingConsole.submit("${CHARLIE} follows ${ALICE}")
        socialNetworkingConsole.submit("${CHARLIE} wall")

        socialNetworkingConsole.submit("${CHARLIE} follows ${BOB}")
        socialNetworkingConsole.submit("${CHARLIE} wall")

        inOrder(timeLinePrinter) {
            verify(timeLinePrinter).print("I love the weather today (5 minutes ago)")
            verify(timeLinePrinter).print("Good game though. (1 minute ago)")
            verify(timeLinePrinter).print("Damn! We lost! (2 minutes ago)")

            verify(timeLinePrinter).print("${CHARLIE} - I'm in New York today! Anyone wants to have a coffee? (2 seconds ago)")
            verify(timeLinePrinter).print("${ALICE} - I love the weather today (5 minutes ago)")

            verify(timeLinePrinter).print("${CHARLIE} - I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)")
            verify(timeLinePrinter).print("${BOB} - Good game though. (1 minute ago)")
            verify(timeLinePrinter).print("${BOB} - Damn! We lost! (2 minutes ago)")
            verify(timeLinePrinter).print("${ALICE} - I love the weather today (5 minutes ago)")
        }
    }

}