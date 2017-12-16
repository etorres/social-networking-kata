package es.eriktorr.katas.core

interface TimeLineReader {

    fun filterBy(vararg users: String): List<TimeLineEntry>

}