package domain.message_handlers

import data.MessageCreateEventTransmitter
import dev.kord.core.event.message.MessageCreateEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

abstract class BaseMessageHandler(messageCreateEventTransmitter: MessageCreateEventTransmitter) {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    abstract val predicate: (MessageCreateEvent) -> Boolean
    private val messages: Flow<MessageCreateEvent> by lazy { messageCreateEventTransmitter.messagesFlow.filter(predicate) }

    fun setup() {
        scope.launch {
            messages.collect { event ->
                handle(event)
            }
        }
    }

    protected abstract suspend fun handle(event: MessageCreateEvent)
}
