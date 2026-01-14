package me.xcue.util.commands

import com.hypixel.hytale.server.core.Message
import com.hypixel.hytale.server.core.command.system.CommandContext
import com.hypixel.hytale.server.core.command.system.CommandSender
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase
import java.util.Random

class HelloCommand : CommandBase("/hello", "Receive a simple greeting!") {
    val greetings = listOf("Hello.", "HI", "Welcome!")

    override fun hasPermission(sender: CommandSender): Boolean {
        return true
    }

    override fun executeSync(ctx: CommandContext) {
        val msg = Message.raw(greetings[Random().nextInt(0, greetings.size)])

        ctx.sendMessage(msg)
    }
}