package me.xcue.util.services

import me.xcue.util.storage.PlayerData
import me.xcue.util.storage.PlayerStorage
import java.util.UUID
import java.util.concurrent.CompletableFuture

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

    fun unloadPlayer(uuid: UUID, save: Boolean = true) {
        CompletableFuture.runAsync {
            if (save) {
                savePlayer(uuid)
            }
        }.whenCompleteAsync { _, _ ->
            cache.remove(uuid)
        }.exceptionallyAsync { e ->
            e.printStackTrace()

            return@exceptionallyAsync null
        }
    }

    fun savePlayer(uuid: UUID) {
        val data = cache[uuid] ?: return

        savePlayer(data)
    }

    fun saveAll() {
        for (data in cache.values) {
            store.save(data)
        }
    }
}