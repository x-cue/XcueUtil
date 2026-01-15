package me.xcue.util.commands.home

import com.hypixel.hytale.component.Ref
import com.hypixel.hytale.component.Store
import com.hypixel.hytale.server.core.Message
import com.hypixel.hytale.server.core.command.system.CommandContext
import com.hypixel.hytale.server.core.command.system.CommandSender
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand
import com.hypixel.hytale.server.core.universe.PlayerRef
import com.hypixel.hytale.server.core.universe.world.World
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore
import me.xcue.util.XcueUtilMod

class HomesCommand: AbstractPlayerCommand("homes", "List your personal homes.") {
    override fun hasPermission(sender: CommandSender): Boolean {
        return true
    }

    override fun execute(
        ctx: CommandContext,
        store: Store<EntityStore?>,
        refStore: Ref<EntityStore?>,
        refPlayer: PlayerRef,
        pWorld: World
    ) {
        val homes = XcueUtilMod.playerService.getPlayer(refPlayer.uuid).homes.keys

        if (!homes.isEmpty()) {
            ctx.sendMessage(Message.raw(homes.joinToString(", ")))
            return
        }

        ctx.sendMessage(Message.raw("You have no homes available!"))
    }
}