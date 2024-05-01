package com.alongo.discordbot.domain.message_handlers

import com.alongo.discordbot.data.MessageCreateEventTransmitter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CreateMessageEventHandler(
    private val messageCreateEventTransmitter: MessageCreateEventTransmitter,
    private val messageHandlerProvider: MessageHandlerProvider
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    fun subscribeToMessageUpdates() {
        messageCreateEventTransmitter.messagesFlow.onEach { event ->
            val msg = event.message.content
            val commandType = CommandTypeResolver.resolve(msg)
            val commandValue = msg.substringAfter(' ')
            commandType?.let { type ->
                messageHandlerProvider.getHandler(type)?.handle(commandValue, event)
            }
        }.launchIn(scope)
    }
}
