package com.alongo.discordbot

import com.alongo.discordbot.data.MessageCreateEventTransmitter
import com.alongo.discordbot.di.*
import com.alongo.discordbot.domain.message_handlers.MessageHandlerFactory
import dev.kord.core.Kord
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.on
import dev.kord.gateway.Intent
import dev.kord.gateway.PrivilegedIntent
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class Application {

    @Inject
    lateinit var kord: Deferred<Kord>

    @Inject
    lateinit var messageCreateEventTransmitter: MessageCreateEventTransmitter

    @Inject
    lateinit var messageHandlerFactory: MessageHandlerFactory

    fun start() = runBlocking {

        DaggerAppComponent.create().inject(this@Application)

        val kord = kord.await()
        messageHandlerFactory.generateMessageHandlers()

        kord.on<MessageCreateEvent> {
            messageCreateEventTransmitter.emit(this)
        }

        kord.login {
            @OptIn(PrivilegedIntent::class)
            intents += Intent.MessageContent
        }
    }
}