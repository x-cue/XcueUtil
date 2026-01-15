package me.xcue.util.lib

import java.time.Duration
import java.time.ZonedDateTime

class DateTimeUtil {
    companion object {
        fun timeSince(time: ZonedDateTime): Duration {
            return Duration.between(time, ZonedDateTime.now())
        }
    }
}