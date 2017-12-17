package es.eriktorr.katas.time

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import java.time.LocalDateTime

class ElapsedTimeCalculatorTest {

    companion object {
        private val YEAR = 2017
        private val MONTH = 12
        private val DAY = 31
        private val HOUR = 23
        private val MINUTE = 59
        private val SECOND = 59

        private val NOW = LocalDateTime.of(YEAR, MONTH, DAY, HOUR, MINUTE, SECOND)
    }

    private val elapsedTimeCalculator = ElapsedTimeCalculator()

    @TestFactory
    fun `calculate the length of time elapsed from when the message was post`() = listOf(
            LocalDateTime.of(YEAR, MONTH, DAY, HOUR, MINUTE, SECOND) to "0 seconds ago",
            LocalDateTime.of(YEAR, MONTH, DAY, HOUR, MINUTE, SECOND-1) to "1 second ago",
            LocalDateTime.of(YEAR, MONTH, DAY, HOUR, MINUTE, SECOND-59) to "59 seconds ago",
            LocalDateTime.of(YEAR, MONTH, DAY, HOUR, MINUTE-1, SECOND) to "1 minute ago",
            LocalDateTime.of(YEAR, MONTH, DAY, HOUR, MINUTE-59, SECOND) to "59 minutes ago",
            LocalDateTime.of(YEAR, MONTH, DAY, HOUR-1, MINUTE, SECOND) to "1 hour ago",
            LocalDateTime.of(YEAR, MONTH, DAY, HOUR-23, MINUTE, SECOND) to "23 hours ago",
            LocalDateTime.of(YEAR, MONTH, DAY-1, HOUR, MINUTE, SECOND) to "1 day ago",
            LocalDateTime.of(YEAR, MONTH, DAY-6, HOUR, MINUTE, SECOND) to "6 days ago",
            LocalDateTime.of(YEAR, MONTH, DAY-7, HOUR, MINUTE, SECOND) to "1 week ago",
            LocalDateTime.of(YEAR, MONTH, DAY-7, HOUR, MINUTE, SECOND-1) to "on December 8th")
            .map { (eventTime, elapsedTime) ->
                DynamicTest.dynamicTest("timeElapsed($eventTime, now) => $elapsedTime") {
                    assertThat(elapsedTimeCalculator.timeElapsedBetween(oldTime = eventTime, newTime = NOW)).isEqualTo(elapsedTime)
                }
            }

}