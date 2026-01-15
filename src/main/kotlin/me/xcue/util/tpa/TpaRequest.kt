package me.xcue.util.tpa

import java.time.ZonedDateTime
import java.util.UUID

class TpaRequest {
    val requester: UUID
    val target: UUID
    val timestamp: ZonedDateTime

    constructor(requester: UUID, target: UUID, timestamp: ZonedDateTime) {
        this.requester = requester
        this.target = target
        this.timestamp = timestamp
    }

    constructor(requester: UUID, target: UUID) : this(requester, target, ZonedDateTime.now())
}
