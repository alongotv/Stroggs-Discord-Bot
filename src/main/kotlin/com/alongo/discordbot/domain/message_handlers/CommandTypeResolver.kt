package com.alongo.discordbot.domain.message_handlers

object CommandTypeResolver {
    private val commands = COMMAND_TYPE.values()

    fun resolve(commandValue: String): COMMAND_TYPE? {
        return commands.firstOrNull {
            commandValue.startsWith(it.command) || (commandValue.contains(it.command) && !commandValue.startsWith(
                '!'
            ))
        }
    }
}
