package me.xcue.util.commands.common

import com.hypixel.hytale.server.core.Message
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgumentType
import com.hypixel.hytale.server.core.command.system.arguments.types.ProcessedArgumentType
import com.hypixel.hytale.server.core.command.system.arguments.types.SingleArgumentType
import com.hypixel.hytale.server.core.universe.Universe
import java.util.UUID

class OnlinePlayerArg : ProcessedArgumentType<String, UUID>(
    "player", Message.raw("/tpa <player>"),
    StringArgumentType("name", "ahh", "bahh"), "/tpa <player>"
) {
    override fun processInput(value: String?): UUID? {
        if (value == null) {
            return null
        }

        for (player in Universe.get().players) {
            if (player.username.lowercase() == value) {
                return player.uuid
            }
        }

        return null
    }
}