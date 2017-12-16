package es.eriktorr.katas.data

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import es.eriktorr.katas.core.Clock
import es.eriktorr.katas.core.TimeLineEntry
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class TimeLineRepositoryTest {

    companion object {
        private val ALICE = "Alice"
        private val MESSAGE = "Good game though."

        private val YEAR = 2017
        private val MONTH = 12
        private val DAY = 15
        private val HOUR = 8
        private val MINUTE = 30
        private val SECOND = 45

        private val TIMESTAMP = LocalDateTime.of(YEAR, MONTH, DAY, HOUR, MINUTE, SECOND)
    }

    private val clock = mock<Clock>()

    private val timeLineRepository = TimeLineRepository(clock)

    @Test
    fun `save user post to time-line`() {
        given(clock.now())
                .willReturn(LocalDateTime.of(YEAR, MONTH, DAY, HOUR, MINUTE, SECOND))

        timeLineRepository.save(ALICE, MESSAGE)

        assertThat(timeLineRepository.filterBy(ALICE)).hasSize(1)
                .containsExactly(TimeLineEntry(timestamp = TIMESTAMP, userName = ALICE, message = MESSAGE))
    }

}