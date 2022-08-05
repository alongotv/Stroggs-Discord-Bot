package domain

import data.MessageCreateEventTransmitter

class MessageHandlerFactory(private val messageCreateEventTransmitter: MessageCreateEventTransmitter) {

    suspend fun generateMessageHandlers() {
        KekMessageHandler(messageCreateEventTransmitter).setup()
    }
}
