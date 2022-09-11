package com.alongo.discordbot.di.domain.message_handlers

import com.alongo.discordbot.data.MessageCreateEventTransmitter
import com.alongo.discordbot.di.QrModule
import com.alongo.discordbot.domain.message_handlers.CreateMessageEventHandler
import com.alongo.discordbot.domain.message_handlers.MessageHandlerProvider
import dagger.Module
import dagger.Provides

@Module(includes = [QrModule::class, MessageHandlersModule::class])
class MessageHandlerFactoryModule {
    @Provides
    fun provideMessageHandlerFactory(
        messageCreateEventTransmitter: MessageCreateEventTransmitter,
        messageHandlerProvider: MessageHandlerProvider
    ) =
        CreateMessageEventHandler(messageCreateEventTransmitter, messageHandlerProvider)
}
