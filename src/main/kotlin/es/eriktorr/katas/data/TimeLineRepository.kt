package es.eriktorr.katas.data

import es.eriktorr.katas.core.TimeLineReader
import es.eriktorr.katas.core.TimeLineRecorder

class TimeLineRepository: TimeLineRecorder, TimeLineReader {

    override fun save(userName: String, message: String) {
        TODO("not implemented")
    }

    override fun <T> filterBy(vararg users: String, block: (Sequence<String>) -> T): Sequence<T> {
        TODO("not implemented")
    }

}