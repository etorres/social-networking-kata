package es.eriktorr.katas.data

import es.eriktorr.katas.core.Clock
import es.eriktorr.katas.core.TimeLineEntry
import es.eriktorr.katas.core.TimeLineReader
import es.eriktorr.katas.core.TimeLineRecorder

class TimeLineRepository(private val clock: Clock): TimeLineRecorder, TimeLineReader {

    private val timeLine = mutableListOf<TimeLineEntry>()

    override fun save(userName: String, message: String) {
        timeLine.add(TimeLineEntry(timestamp = clock.now(), userName = userName, message = message))
    }

    override fun filterBy(vararg users: String): List<TimeLineEntry> = timeLine.filter { users.contains(it.userName) }

}