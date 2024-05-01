package com.alongo.discordbot.domain.message_handlers.misc

import com.alongo.discordbot.domain.message_handlers.BaseMessageHandler
import dev.kord.core.event.message.MessageCreateEvent

class HelpMessageHandler : BaseMessageHandler() {

    override suspend fun handle(command: String, event: MessageCreateEvent) {
    }
}
