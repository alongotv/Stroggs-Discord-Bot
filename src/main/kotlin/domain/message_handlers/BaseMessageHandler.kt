package domain.message_handlers

import data.MessageCreateEventTransmitter
import dev.kord.core.event.message.MessageCreateEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

abstract class BaseMessageHandler(private val messageCreateEventTransmitter: MessageCreateEventTransmitter) {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    abstract val predicate: (MessageCreateEvent) -> Boolean

    fun setup() {
        scope.launch {
            messageCreateEventTransmitter.messagesFlow.filter(predicate).collect { event ->
                handle(event)
            }
        }
    }

    protected abstract suspend fun handle(event: MessageCreateEvent)
}
