package me.xcue.util.tpa

import me.xcue.util.lib.DateTimeUtil
import java.util.UUID

class TpaService {
    val timerSeconds: Int = 5
    val requestsByTarget: MutableMap<UUID, TpaRequest> = mutableMapOf()

    fun tryAccept(targetId: UUID): TpaRequest? {
        val req = requestsByTarget[targetId] ?: return null

        return if (DateTimeUtil.timeSince(req.timestamp).seconds <= timerSeconds) req else null
    }

    fun deny(targetId: UUID) {
        requestsByTarget.remove(targetId)
    }

    fun tpa(requester: UUID, target: UUID) {
        requestsByTarget[target] = TpaRequest(requester, target)
    }
}