package es.eriktorr.katas.data

import es.eriktorr.katas.core.TimeLineEntry
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class TimeLineRepositoryTest {

    companion object {
        private val ALICE = "Alice"
        private val BOB = "Bob"

        private val MESSAGE_1 = "I love the weather today"
        private val MESSAGE_2 = "Damn! We lost!"
        private val MESSAGE_3 = "Good game though."

        private val YEAR = 2017
        private val MONTH = 12
        private val DAY = 15
        private val HOUR = 8
        private val MINUTE = 30
        private val SECOND = 45

        private val TIMESTAMP_1 = LocalDateTime.of(YEAR, MONTH, DAY, HOUR, MINUTE, SECOND-2)
        private val TIMESTAMP_2 = LocalDateTime.of(YEAR, MONTH, DAY, HOUR, MINUTE, SECOND-1)
        private val TIMESTAMP_3 = LocalDateTime.of(YEAR, MONTH, DAY, HOUR, MINUTE, SECOND)
    }

    private val timeLineRepository = TimeLineRepository()

    @Test
    fun `save user post to time-line`() {
        timeLineRepository.save(TIMESTAMP_1, userName = ALICE, message = MESSAGE_1)

        assertThat(timeLineRepository.filterBy(ALICE)).hasSize(1)
                .containsExactly(TimeLineEntry(TIMESTAMP_1, userName = ALICE, message = MESSAGE_1))
    }

    @Test
    fun `filter time-line by user`() {
        timeLineRepository.save(TIMESTAMP_1, userName = ALICE, message = MESSAGE_1)
        timeLineRepository.save(TIMESTAMP_2, userName = BOB, message = MESSAGE_2)
        timeLineRepository.save(TIMESTAMP_3, userName = BOB, message = MESSAGE_3)

        assertThat(timeLineRepository.filterBy(BOB)).hasSize(2)
                .containsExactly(
                        TimeLineEntry(TIMESTAMP_2, userName = BOB, message = MESSAGE_2),
                        TimeLineEntry(TIMESTAMP_3, userName = BOB, message = MESSAGE_3)
                )
    }

}