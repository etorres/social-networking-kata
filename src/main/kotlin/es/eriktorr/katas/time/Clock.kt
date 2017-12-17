package es.eriktorr.katas.time

import java.time.LocalDateTime

interface Clock {

    fun now(): LocalDateTime

}