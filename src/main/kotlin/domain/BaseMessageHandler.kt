package domain

import data.MessageCreateEventTransmitter
import dev.kord.core.event.message.MessageCreateEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter

abstract class BaseMessageHandler(messageCreateEventTransmitter: MessageCreateEventTransmitter) {

    protected val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    abstract val predicate: (MessageCreateEvent) -> Boolean
    val messages: Flow<MessageCreateEvent> by lazy { messageCreateEventTransmitter.messagesFlow.filter(predicate) }

    abstract suspend fun setup()
}
