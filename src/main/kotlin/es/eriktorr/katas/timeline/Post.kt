package es.eriktorr.katas.timeline

import java.time.LocalDateTime

data class Post(val timestamp: LocalDateTime = LocalDateTime.now(), val userName: String, val message: String)