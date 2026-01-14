package me.xcue.util.commands.home

import com.hypixel.hytale.component.Ref
import com.hypixel.hytale.component.Store
import com.hypixel.hytale.math.vector.Location
import com.hypixel.hytale.server.core.Message
import com.hypixel.hytale.server.core.command.system.CommandContext
import com.hypixel.hytale.server.core.command.system.CommandSender
import com.hypixel.hytale.server.core.command.system.arguments.system.DefaultArg
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand
import com.hypixel.hytale.server.core.universe.PlayerRef
import com.hypixel.hytale.server.core.universe.world.World
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore
import me.xcue.util.XcueUtilMod
import me.xcue.util.services.PlayerService

class SetHomeCommand: AbstractPlayerCommand("sethome", "Binds /home to your current location.") {
    private val nameArg: DefaultArg<String> =
        withDefaultArg("name", "Name of the home", HomeArg(), "home", "")

    override fun hasPermission(sender: CommandSender): Boolean {
        return true
    }

    override fun execute(
        ctx: CommandContext,
        store: Store<EntityStore?>,
        refStore: Ref<EntityStore?>,
        pRef: PlayerRef,
        pWorld: World
    ) {
        val uuid = pRef.uuid
        val loc = Location(pWorld.name, pRef.transform.clone())
        val name = ctx.get(nameArg)

        XcueUtilMod.playerService.getPlayer(uuid).homes[name] = loc

        ctx.sendMessage(Message.raw("Your home ($name) has been updated!"))
    }
}