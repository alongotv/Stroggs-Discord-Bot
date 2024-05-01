package com.alongo.discordbot.domain.messagehandlers

import com.alongo.discordbot.data.MessageCreateEventTransmitter
import com.alongo.discordbot.feature.command.audio.AudioFeatureCommands
import com.alongo.discordbot.feature.command.misc.FunFeatureCommands
import com.alongo.discordbot.feature.command.qr.QrFeatureCommands
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
    private val commandTypeResolver = CommandTypeResolver

    init {
        commandTypeResolver.setup(
            FunFeatureCommands.get() +
                    AudioFeatureCommands.get() +
                    QrFeatureCommands.get(),
            commandMarker = COMMAND_MARKER
        )
    }

    fun subscribeToMessageUpdates() {
        messageCreateEventTransmitter.messagesFlow.onEach { event ->
            val msg = event.message.content
            val commandType = commandTypeResolver.resolve(msg)
            val commandValue = msg.substringAfter(' ')
            commandType?.let { type ->
                messageHandlerProvider.getHandler(type)?.handle(commandValue, event)
            }
        }.launchIn(scope)
    }
}

private const val COMMAND_MARKER = "!"
