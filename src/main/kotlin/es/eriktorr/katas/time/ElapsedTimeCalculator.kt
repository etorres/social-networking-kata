package es.eriktorr.katas.time

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit.*

class ElapsedTimeCalculator {

    companion object {
        private val DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM d, yyyy HH:mm")
    }

    fun timeElapsedBetween(oldTime: LocalDateTime, newTime: LocalDateTime): String {
        val elapsedTime = ElapsedTime(oldTime, newTime)
        return when {
            elapsedTime.seconds < 60L -> "${elapsedTime.seconds} second${plural(elapsedTime.seconds)} ago"
            elapsedTime.minutes < 60L -> "${elapsedTime.minutes} minute${plural(elapsedTime.minutes)} ago"
            elapsedTime.hours < 24L -> "${elapsedTime.hours} hour${plural(elapsedTime.hours)} ago"
            elapsedTime.days < 7L -> "${elapsedTime.days} day${plural(elapsedTime.days)} ago"
            elapsedTime.days == 7L -> "1 week ago"
            else -> "on " + oldTime.format(DATE_TIME_FORMATTER)
        }
    }

    private fun plural(time: Long) = if (time != 1L) "s" else ""

    data class ElapsedTime(private val oldTime: LocalDateTime, private val newTime: LocalDateTime) {
        val days: Long = DAYS.between(oldTime, newTime)
        val hours: Long = HOURS.between(oldTime, newTime)
        val minutes: Long = MINUTES.between(oldTime, newTime)
        val seconds: Long = SECONDS.between(oldTime, newTime)
    }

}