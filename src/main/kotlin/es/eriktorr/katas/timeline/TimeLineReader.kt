package es.eriktorr.katas.timeline

interface TimeLineReader {

    fun <T> useEntries(vararg users: String, block: (List<TimeLineEntry>) -> T)

}