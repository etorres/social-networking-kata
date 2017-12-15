package es.eriktorr.katas

import com.nhaarman.mockito_kotlin.inOrder
import com.nhaarman.mockito_kotlin.mock
import org.junit.jupiter.api.Test

class SocialNetworkingConsoleTest {

    private val clock = mock<Clock>()

    private val timeLinePrinter = mock<TimeLinePrinter>()

    @Test
    fun `a user can publish messages to a personal time-line`() {
        val socialNetworkingConsole = SocialNetworkingConsole(clock, timeLinePrinter)

        socialNetworkingConsole.submit("Alice -> I love the weather today")
        socialNetworkingConsole.submit("Bob -> Damn! We lost!")
        socialNetworkingConsole.submit("Bob -> Good game though.")

        socialNetworkingConsole.submit("Alice")
        socialNetworkingConsole.submit("Bob")

        inOrder(timeLinePrinter) {
            verify(timeLinePrinter).println("I love the weather today (5 minutes ago)")
            verify(timeLinePrinter).println("Good game though. (1 minute ago)")
            verify(timeLinePrinter).println("Damn! We lost! (2 minutes ago)")
        }
    }

}