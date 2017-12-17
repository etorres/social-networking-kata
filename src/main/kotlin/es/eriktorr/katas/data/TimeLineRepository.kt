package es.eriktorr.katas.data

import es.eriktorr.katas.timeline.TimeLineEntry
import es.eriktorr.katas.timeline.TimeLineReader
import es.eriktorr.katas.timeline.TimeLineRecorder
import java.time.LocalDateTime

class TimeLineRepository: TimeLineRecorder, TimeLineReader {
    private val timeLine = mutableListOf<TimeLineEntry>()

    override fun save(timestamp: LocalDateTime, userName: String, message: String) {
        timeLine.add(TimeLineEntry(timestamp = timestamp, userName = userName, message = message))
    }

    override fun <T> useEntries(vararg users: String, block: (List<TimeLineEntry>) -> T) {
        block(timeLine.filter { users.contains(it.userName) }.sortedByDescending { it.timestamp })
    }

}