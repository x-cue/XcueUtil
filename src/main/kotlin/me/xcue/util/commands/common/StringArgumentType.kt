package me.xcue.util.commands.common

import com.hypixel.hytale.server.core.command.system.ParseResult
import com.hypixel.hytale.server.core.command.system.arguments.types.SingleArgumentType

class StringArgumentType: SingleArgumentType<String> {
    constructor(name: String, usage: String, examples: String) : super(name, usage, examples) {

    }

    override fun parse(
        p0: String?,
        p1: ParseResult?
    ): String? {
        return p0
    }
}