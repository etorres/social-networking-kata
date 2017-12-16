package es.eriktorr.katas.core

import java.time.LocalDateTime

interface TimeLineRecorder {

    fun save(timestamp: LocalDateTime, userName: String, message: String)

}