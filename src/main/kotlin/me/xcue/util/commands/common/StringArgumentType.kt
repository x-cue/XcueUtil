package me.xcue.util.commands.common

import com.hypixel.hytale.server.core.command.system.ParseResult
import com.hypixel.hytale.server.core.command.system.arguments.types.SingleArgumentType

class StringArgumentType: SingleArgumentType<String> {
    /**
     * Use this constructor to customize the display of the argument
     */
    constructor(name: String, usage: String, examples: String) : super(name, usage, examples) {

    }

    /**
     * Use this constructor for ProcessingArgumentTypes
     */
    constructor() : super("", "", "")

    override fun parse(
        p0: String?,
        p1: ParseResult?
    ): String? {
        return p0
    }
}