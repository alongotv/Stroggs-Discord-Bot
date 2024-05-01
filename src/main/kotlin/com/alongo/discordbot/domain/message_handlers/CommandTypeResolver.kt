package com.alongo.discordbot.domain.message_handlers

import com.alongo.discordbot.feature.command.Command
import io.ktor.util.toLowerCasePreservingASCIIRules

object CommandTypeResolver {

    // Marked commands are used in the beginning of the message
    // and require a special command marker symbol (e.g. '!')
    private val markedCommands = mutableMapOf<String, Command>()

    // Unmarked commands can be placed anywhere in the message
    private val unmarkedCommands = mutableListOf<Pair<String, Command>>()

    private lateinit var commandMarker: String

    fun setup(commandStorage: List<Command>, commandMarker: String) {
        this.commandMarker = commandMarker
        commandStorage.forEach { command ->
            command.keywords.forEach {
                if (command.requireCommandMarker) {
                    markedCommands[it] = command
                } else {
                    unmarkedCommands.add(it to command)
                }
            }
        }
    }

    fun resolve(query: String): Command? {
        val lowercaseQuery = query.toLowerCasePreservingASCIIRules()
        val hasPrefix = lowercaseQuery.startsWith(commandMarker)

        // Get the first word of the message
        val commandWithoutPrefix =
            lowercaseQuery.removePrefix(commandMarker).split(" ").firstOrNull() ?: return null

        val markedCommand = if (hasPrefix) markedCommands[commandWithoutPrefix] else null
        return markedCommand ?: unmarkedCommands.find {
            // Iterate over unmarked commands to see
            lowercaseQuery.contains(it.first)
        }?.second
    }
}
