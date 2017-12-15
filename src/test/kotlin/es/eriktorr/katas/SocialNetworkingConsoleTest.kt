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

}