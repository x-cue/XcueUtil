package me.xcue.util.storage

import java.util.*

interface PlayerStorage {
    fun load(uuid: UUID?): PlayerData?
    fun save(data: PlayerData)
    fun loadAll(): MutableList<PlayerData>
}