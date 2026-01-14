package me.xcue.util.commands.home

import com.hypixel.hytale.component.Ref
import com.hypixel.hytale.component.Store
import com.hypixel.hytale.server.core.Message
import com.hypixel.hytale.server.core.command.system.CommandContext
import com.hypixel.hytale.server.core.command.system.CommandSender
import com.hypixel.hytale.server.core.command.system.arguments.system.DefaultArg
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand
import com.hypixel.hytale.server.core.entity.entities.Player
import com.hypixel.hytale.server.core.modules.entity.teleport.Teleport
import com.hypixel.hytale.server.core.universe.PlayerRef
import com.hypixel.hytale.server.core.universe.Universe
import com.hypixel.hytale.server.core.universe.world.World
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore
import me.xcue.util.XcueUtilMod
import me.xcue.util.services.PlayerService

class HomeCommand : AbstractPlayerCommand("home", "Teleport to one of your homes") {
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
        try {
            if (!ctx.isPlayer) {
                ctx.sendMessage(Message.raw("Only a player can execute this command!"))
                return
            }

            val p = ctx.senderAs(Player::class.java)
            val uuid = pRef.uuid

            val pData = XcueUtilMod.playerService.getPlayer(uuid)

            val name = ctx.get(nameArg)
            val loc = pData.homes[name]

            if (loc == null) {
                ctx.sendMessage(Message.raw("Home not found with name '$name'"))
                return
            }

            val world = Universe.get().getWorld(loc.world)
                ?: throw Exception("Error parsing world from saved home ${p.displayName}:$name")

            store.addComponent(refStore, Teleport.getComponentType(), Teleport(world, loc.position, loc.rotation))
        } catch (e: Exception) {
            ctx.sendMessage(Message.raw("Something went wrong: ${e.message}"))
        }
    }
}