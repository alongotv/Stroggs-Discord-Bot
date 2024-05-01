package com.alongo.discordbot.domain.messagehandlers.misc

import com.alongo.discordbot.domain.messagehandlers.BaseMessageHandler
import dev.kord.core.event.message.MessageCreateEvent

class HelpMessageHandler : BaseMessageHandler() {

    override suspend fun handle(command: String, event: MessageCreateEvent) {
        println("no-op")
    }
}
