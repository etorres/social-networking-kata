package es.eriktorr.katas.core

import java.time.LocalDateTime

interface Clock {

    fun now(): LocalDateTime

}