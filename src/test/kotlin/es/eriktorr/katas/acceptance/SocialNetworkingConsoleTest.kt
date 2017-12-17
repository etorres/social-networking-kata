package es.eriktorr.katas.acceptance

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.inOrder
import com.nhaarman.mockito_kotlin.mock
import es.eriktorr.katas.core.Clock
import es.eriktorr.katas.core.CommandBuilder
import es.eriktorr.katas.SocialNetworkingConsole
import es.eriktorr.katas.core.TimeLinePrinter
import es.eriktorr.katas.data.TimeLineRepository
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class SocialNetworkingConsoleTest {

    companion object {
        private val ALICE = "Alice"
        private val BOB = "Bob"
        private val CHARLIE = "Charlie"

        private val YEAR = 2017
        private val MONTH = 12
        private val DAY = 15
        private val HOUR = 8
        private val MINUTE = 30
        private val SECOND = 45
    }

    private val clock = mock<Clock>()

    private val timeLinePrinter = mock<TimeLinePrinter>()

    private val socialNetworkingConsole = SocialNetworkingConsole(clock, CommandBuilder(), TimeLineRepository(), timeLinePrinter)

    /* TODO
    a user can publish messages to a personal time-line
    a user can subscribe to other users time-lines, and view an aggregated list of all subscriptions
     */

    @Test
    fun `a user can publish messages to, view and subscribe to a personal time-line`() {
        given(clock.now())
                .willReturn(LocalDateTime.of(YEAR, MONTH, DAY, HOUR, MINUTE -5, SECOND))
                .willReturn(LocalDateTime.of(YEAR, MONTH, DAY, HOUR, MINUTE -2, SECOND))
                .willReturn(LocalDateTime.of(YEAR, MONTH, DAY, HOUR, MINUTE -1, SECOND))
                .willReturn(LocalDateTime.of(YEAR, MONTH, DAY, HOUR, MINUTE, SECOND))
                .willReturn(LocalDateTime.of(YEAR, MONTH, DAY, HOUR, MINUTE, SECOND))
                .willReturn(LocalDateTime.of(YEAR, MONTH, DAY, HOUR, MINUTE, SECOND -2))
                .willReturn(LocalDateTime.of(YEAR, MONTH, DAY, HOUR, MINUTE, SECOND))
                .willReturn(LocalDateTime.of(YEAR, MONTH, DAY, HOUR, MINUTE, SECOND))

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

            verify(timeLinePrinter).print("${CHARLIE} - I'm in New York today! Anyone wants to have a coffee? (2 seconds ago)")
            verify(timeLinePrinter).print("${BOB} - Good game though. (1 minute ago)")
            verify(timeLinePrinter).print("${BOB} - Damn! We lost! (2 minutes ago)")
            verify(timeLinePrinter).print("${ALICE} - I love the weather today (5 minutes ago)")
        }
    }

}