package me.xcue.util.commands

import com.hypixel.hytale.server.core.Message
import com.hypixel.hytale.server.core.command.system.CommandContext
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase
import com.hypixel.hytale.server.core.entity.entities.Player

class HomeCommand : CommandBase("home", "teleport to one of your beds at random") {
    override fun executeSync(ctx: CommandContext) {
        if (!ctx.isPlayer) {
            ctx.sendMessage(Message.raw("Only a player can execute this command!"))
            return
        }

        val player = ctx.sender() as Player
        // player.moveTo
//        player.addLocationChange()
    }


}