package es.eriktorr.katas.core

interface TimeLineReader {

    fun <T> useEntries(vararg users: String, block: (List<TimeLineEntry>) -> T)

}