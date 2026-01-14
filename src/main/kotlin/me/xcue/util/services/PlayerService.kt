package me.xcue.util.services

import me.xcue.util.storage.PlayerData
import me.xcue.util.storage.PlayerStorage
import java.util.UUID

class PlayerService {
    private val store: PlayerStorage
    private val cache: MutableMap<UUID, PlayerData>

    constructor(store: PlayerStorage) {
        this.store = store
        this.cache = mutableMapOf()
//        loadAll()
    }

    private fun loadAll() {
        for (data in store.loadAll()) {
            cache[data.uuid] = data
        }
    }
// save on player leave
    // load on player join

    fun getPlayer(uuid: UUID): PlayerData {
        return cache.computeIfAbsent(uuid) {id ->
            val data = store.load(id)
            return@computeIfAbsent data ?: PlayerData(id)
        }
    }

    fun savePlayer(data: PlayerData) {
        cache[data.uuid] = data
        store.save(data)
    }

    fun saveAll() {
        for (data in cache.values) {
            store.save(data)
        }
    }
}