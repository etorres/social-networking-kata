package es.eriktorr.katas.core

interface TimeLineReader {

    fun <T> filterBy(vararg users: String, block: (Sequence<String>) -> T): Sequence<T>

}