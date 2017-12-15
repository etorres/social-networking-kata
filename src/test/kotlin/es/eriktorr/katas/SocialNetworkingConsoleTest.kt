package es.eriktorr.katas

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.inOrder
import com.nhaarman.mockito_kotlin.mock
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class SocialNetworkingConsoleTest {

    private val clock = mock<Clock>()

    private val timeLinePrinter = mock<TimeLinePrinter>()

    private val socialNetworkingConsole = SocialNetworkingConsole(clock, timeLinePrinter)

    @Test
    fun `a user can publish messages to a personal time-line`() {
        given(clock.now())
                .willReturn(LocalDateTime.of(2017, 12, 15, 8, 30, 10))
                .willReturn(LocalDateTime.of(2017, 12, 15, 8, 31, 10))
                .willReturn(LocalDateTime.of(2017, 12, 15, 8, 32, 10))
                .willReturn(LocalDateTime.of(2017, 12, 15, 8, 35, 10))
                .willReturn(LocalDateTime.of(2017, 12, 15, 8, 33, 10))

        socialNetworkingConsole.submit("Alice -> I love the weather today")
        socialNetworkingConsole.submit("Bob -> Damn! We lost!")
        socialNetworkingConsole.submit("Bob -> Good game though.")

        socialNetworkingConsole.submit("Alice")
        socialNetworkingConsole.submit("Bob")

        inOrder(timeLinePrinter) {
            verify(timeLinePrinter).print("I love the weather today (5 minutes ago)")
            verify(timeLinePrinter).print("Good game though. (1 minute ago)")
            verify(timeLinePrinter).print("Damn! We lost! (2 minutes ago)")
        }
    }

    @Test
    fun `a user can subscribe to other users time-lines, and view an aggregated list of all subscriptions`() {
        socialNetworkingConsole.submit("Charlie -> I'm in New York today! Anyone wants to have a coffee?")
        socialNetworkingConsole.submit("Charlie wall")
        socialNetworkingConsole.submit("Charlie follows Bob")
        socialNetworkingConsole.submit("Charlie wall")

        inOrder(timeLinePrinter) {
            verify(timeLinePrinter).print("Charlie follows Alice")
            verify(timeLinePrinter).print("Charlie - I'm in New York today! Anyone wants to have a coffee? (2 seconds ago)")
            verify(timeLinePrinter).print("Alice - I love the weather today (5 minutes ago)")
            verify(timeLinePrinter).print("Charlie - I'm in New York today! Anyone wants to have a coffee? (15 seconds ago)")
            verify(timeLinePrinter).print("Bob - Good game though. (1 minute ago)")
            verify(timeLinePrinter).print("Bob - Damn! We lost! (2 minutes ago)")
            verify(timeLinePrinter).print("Alice - I love the weather today (5 minutes ago)")
        }
    }

}