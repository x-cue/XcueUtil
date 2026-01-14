package me.xcue.util.commands.home

import com.hypixel.hytale.server.core.command.system.ParseResult
import com.hypixel.hytale.server.core.command.system.arguments.types.SingleArgumentType

class HomeArg : SingleArgumentType<String> {
    constructor() : super("name", "home <name>", "/home base") {

    }


    override fun parse(p0: String?, p1: ParseResult?): String? {
        return p0
    }
}