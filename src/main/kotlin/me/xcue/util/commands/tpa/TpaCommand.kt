package me.xcue.util.commands.tpa

import com.hypixel.hytale.component.Ref
import com.hypixel.hytale.component.Store
import com.hypixel.hytale.server.core.Message
import com.hypixel.hytale.server.core.command.system.CommandContext
import com.hypixel.hytale.server.core.command.system.CommandSender
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand
import com.hypixel.hytale.server.core.universe.PlayerRef
import com.hypixel.hytale.server.core.universe.Universe
import com.hypixel.hytale.server.core.universe.world.World
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore
import me.xcue.util.XcueUtilMod
import me.xcue.util.commands.common.OnlinePlayerArg

class TpaCommand : AbstractPlayerCommand("tpa", "Send a teleportation request to another player.") {
    val playerArg = withRequiredArg("player", "Name of the player", OnlinePlayerArg())

    override fun hasPermission(sender: CommandSender): Boolean {
        return true
    }

    override fun execute(
        ctx: CommandContext,
        store: Store<EntityStore?>,
        storeRef: Ref<EntityStore?>,
        pRef: PlayerRef,
        pWorld: World
    ) {
        val targetId = ctx.get(playerArg) ?: return ctx.sendMessage(Message.raw("That player is not online."))
        val target =
            Universe.get().getPlayer(targetId) ?: return ctx.sendMessage(Message.raw("That player is not online."))
        if (targetId.toString() == pRef.uuid.toString()) return ctx.sendMessage(Message.raw("You cannot teleport to yourself."))


        XcueUtilMod.tpaService.tpa(pRef.uuid, targetId)
        ctx.sendMessage(Message.raw("Sent a teleportation request to ${target.username}"))
        target.sendMessage(Message.raw("You have received an incoming teleportation request from ${pRef.username}. You have 5 seconds to /tpaccept"))
    }
}