package me.xcue.util.commands.home

import com.hypixel.hytale.component.Ref
import com.hypixel.hytale.component.Store
import com.hypixel.hytale.server.core.Message
import com.hypixel.hytale.server.core.command.system.CommandContext
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand
import com.hypixel.hytale.server.core.universe.PlayerRef
import com.hypixel.hytale.server.core.universe.world.World
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore
import me.xcue.util.XcueUtilMod
import me.xcue.util.services.PlayerService

class DelHomeCommand: AbstractPlayerCommand("delhome", "Delete one of your homes") {
    private val nameArg: RequiredArg<String> = withRequiredArg("name", "Name of the home", HomeArg())

    override fun execute(
        ctx: CommandContext,
        store: Store<EntityStore?>,
        refStore: Ref<EntityStore?>,
        pRef: PlayerRef,
        pWorld: World
    ) {
        val uuid = pRef.uuid
        val name = ctx.get(nameArg)

        XcueUtilMod.playerService.getPlayer(uuid).homes.remove(name)

        ctx.sendMessage(Message.raw("Deleted home $name!"))
    }
}