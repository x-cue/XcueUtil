package me.xcue.util.storage

import com.hypixel.hytale.math.vector.Location
import java.util.UUID

class PlayerData {
    val uuid: UUID
    val homes: MutableMap<String, Location>

    constructor(uuid: UUID, homes: MutableMap<String, Location>? = null) {
        this.uuid = uuid

        if (homes != null) {
            this.homes = homes
        } else {
            this.homes = mutableMapOf()
        }
    }
}