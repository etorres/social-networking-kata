package es.eriktorr.katas.data

import es.eriktorr.katas.core.TimeLineEntry
import es.eriktorr.katas.core.TimeLineReader
import es.eriktorr.katas.core.TimeLineRecorder
import java.time.LocalDateTime

class TimeLineRepository: TimeLineRecorder, TimeLineReader {

    private val timeLine = mutableListOf<TimeLineEntry>()

    override fun save(timestamp: LocalDateTime, userName: String, message: String) {
        timeLine.add(TimeLineEntry(timestamp = timestamp, userName = userName, message = message))
    }

    override fun filterBy(vararg users: String): List<TimeLineEntry> = timeLine.filter { users.contains(it.userName) }

}