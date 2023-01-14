package com.alongo.discordbot.domain.message_handlers

import com.alongo.discordbot.data.MessageCreateEventTransmitter
import io.ktor.util.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class CreateMessageEventHandler(
    private val messageCreateEventTransmitter: MessageCreateEventTransmitter,
    private val messageHandlerProvider: MessageHandlerProvider
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    fun subscribeToMessageUpdates() {
        scope.launch {
            messageCreateEventTransmitter.messagesFlow.collect { event ->
                val msg = event.message.content

                val commandType = CommandTypeResolver().resolve(msg.toLowerCasePreservingASCIIRules())
                val commandValue = msg.substringAfter(' ')

                commandType?.let { type -> messageHandlerProvider.getHandler(type)?.handle(commandValue, event) }
            }
        }
    }
}
