package me.xcue.util

import com.hypixel.hytale.component.Store
import com.hypixel.hytale.logger.HytaleLogger
import com.hypixel.hytale.protocol.Position
import com.hypixel.hytale.server.core.event.events.player.PlayerConnectEvent
import com.hypixel.hytale.server.core.event.events.player.PlayerDisconnectEvent
import com.hypixel.hytale.server.core.plugin.JavaPlugin
import com.hypixel.hytale.server.core.plugin.JavaPluginInit
import me.xcue.util.commands.HelloCommand
import me.xcue.util.commands.home.DelHomeCommand
import me.xcue.util.commands.home.HomeCommand
import me.xcue.util.commands.home.HomesCommand
import me.xcue.util.commands.home.SetHomeCommand
import me.xcue.util.commands.tpa.TpAcceptCommand
import me.xcue.util.commands.tpa.TpaCommand
import me.xcue.util.services.PlayerService
import me.xcue.util.storage.PlayerJsonStorage
import me.xcue.util.tpa.TpaService
import java.io.File

/* This is the main class: the entry point for your plugin.
 * Use the setup function to register commands or event listeners.
 */

class XcueUtilMod(init: JavaPluginInit) : JavaPlugin(init) {
    companion object {
        private val LOGGER: HytaleLogger = HytaleLogger.forEnclosingClass()
        lateinit var playerService: PlayerService
        lateinit var tpaService: TpaService
    }

    override fun setup() {
        playerService = PlayerService(PlayerJsonStorage(dataDirectory.toFile()))
        tpaService = TpaService()

        registerCommands()
        registerListeners()
    }

    override fun shutdown() {
//        super.shutdown()
        playerService.saveAll()
    }


    private fun registerCommands() {
        commandRegistry.registerCommand(HelloCommand())
        commandRegistry.registerCommand(HomeCommand())
        commandRegistry.registerCommand(DelHomeCommand())
        commandRegistry.registerCommand(SetHomeCommand())
        commandRegistry.registerCommand(HomesCommand())

        commandRegistry.registerCommand(TpaCommand())
        commandRegistry.registerCommand(TpAcceptCommand())
    }

    private fun registerListeners() {
        // Later will abstract this
        eventRegistry.register(PlayerConnectEvent::class.java) { e ->
            playerService.getPlayer(e.playerRef.uuid)
        }

        eventRegistry.register(PlayerDisconnectEvent::class.java) { e ->
            playerService.savePlayer(e.playerRef.uuid)
        }
    }
}

