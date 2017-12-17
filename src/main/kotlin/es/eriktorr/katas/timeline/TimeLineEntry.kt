package es.eriktorr.katas.timeline

import java.time.LocalDateTime

data class TimeLineEntry(val timestamp: LocalDateTime = LocalDateTime.now(), val userName: String, val message: String)