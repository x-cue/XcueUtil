package me.xcue.util.commands.tpa

import com.hypixel.hytale.component.Ref
import com.hypixel.hytale.component.Store
import com.hypixel.hytale.server.core.Message
import com.hypixel.hytale.server.core.command.system.CommandContext
import com.hypixel.hytale.server.core.command.system.CommandSender
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand
import com.hypixel.hytale.server.core.modules.entity.teleport.Teleport
import com.hypixel.hytale.server.core.universe.PlayerRef
import com.hypixel.hytale.server.core.universe.Universe
import com.hypixel.hytale.server.core.universe.world.World
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore
import me.xcue.util.XcueUtilMod

class TpAcceptCommand : AbstractPlayerCommand("tpaccept", "Accept an incoming teleportation request.") {
    override fun hasPermission(sender: CommandSender): Boolean {
        return true
    }

    override fun execute(
        ctx: CommandContext,
        store: Store<EntityStore?>,
        ref: Ref<EntityStore?>,
        pRef: PlayerRef,
        pWorld: World
    ) {
        val tpaService = XcueUtilMod.tpaService
        val req =
            tpaService.tryAccept(pRef.uuid) ?: return ctx.sendMessage(Message.raw("You have no pending requests."))


        ctx.sendMessage(Message.raw("Accepted the incoming teleportation request!"))

        val requester =
            Universe.get().getPlayer(req.requester) ?: return ctx.sendMessage(Message.raw("The requester is offline."))
        val tform = pRef.transform

        requester.reference?.store?.addComponent(
            ref,
            Teleport.getComponentType(),
            Teleport(pWorld, tform.position, tform.rotation)
        )
    }
}