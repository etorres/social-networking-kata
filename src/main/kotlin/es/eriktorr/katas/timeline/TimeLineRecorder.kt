package es.eriktorr.katas.timeline

import java.time.LocalDateTime

interface TimeLineRecorder {

    fun save(timestamp: LocalDateTime, userName: String, message: String)

}