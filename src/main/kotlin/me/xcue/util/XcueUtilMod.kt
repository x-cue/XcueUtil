package me.xcue.util

import com.hypixel.hytale.logger.HytaleLogger
import com.hypixel.hytale.server.core.plugin.JavaPlugin
import com.hypixel.hytale.server.core.plugin.JavaPluginInit
import me.xcue.util.commands.HelloCommand

/* This is the main class: the entry point for your plugin.
 * Use the setup function to register commands or event listeners.
 */

class XcueUtilMod(init: JavaPluginInit) : JavaPlugin(init) {
    companion object {
        private val LOGGER: HytaleLogger = HytaleLogger.forEnclosingClass()
    }

    init {

    }

    override fun setup() {
        commandRegistry.registerCommand(HelloCommand())
    }
}

