package es.eriktorr.katas.timeline

import java.time.LocalDateTime

interface PostsRecorder {

    fun save(timestamp: LocalDateTime, userName: String, message: String)

}