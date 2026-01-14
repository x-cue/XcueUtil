package me.xcue.util.storage

import com.google.gson.Gson
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException
import java.util.UUID

class PlayerJsonStorage : me.xcue.util.storage.PlayerStorage {
    private var dataFolder: File

    constructor(dataFolder: File) {
        this.dataFolder = File(dataFolder, "players")
        this.dataFolder.mkdirs()
    }

    override fun load(uuid: UUID?): PlayerData? {
        val file = File(dataFolder, "$uuid.json")
        if (!file.exists()) return null

        try {
            BufferedReader(FileReader(file)).use { br ->
                return Gson().fromJson(br, PlayerData::class.java)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return null
    }

    override fun save(data: PlayerData) {
        val file = File(dataFolder, "${data.uuid}.json")

        try {
            BufferedWriter(FileWriter(file)).use { bw ->
                Gson().toJson(data, bw)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun loadAll(): MutableList<PlayerData> {
        val data: MutableList<PlayerData> = mutableListOf()
        val files = dataFolder.listFiles{_, name ->
            name.endsWith(".json")
        }

        if (files != null) {
            for (file in files) {
                try {
                    BufferedReader(FileReader(file)).use {br ->
                        data.add(Gson().fromJson(br, PlayerData::class.java))
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }

        return data
    }
}