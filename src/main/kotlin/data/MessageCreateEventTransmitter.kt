package data

import dev.kord.core.event.message.MessageCreateEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class MessageCreateEventTransmitter {
    private val _messagesFlow = MutableSharedFlow<MessageCreateEvent>()
    val messagesFlow: SharedFlow<MessageCreateEvent> = _messagesFlow

    suspend fun emit(event: MessageCreateEvent, ignoresBots: Boolean = true) {
        if (event.message.author?.isBot == true && ignoresBots) return
        _messagesFlow.emit(event)
    }
}
