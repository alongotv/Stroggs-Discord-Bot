package com.alongo.discordbot.di.domain.messagehandlers

import com.alongo.discordbot.data.MessageCreateEventTransmitter
import com.alongo.discordbot.di.QrModule
import com.alongo.discordbot.domain.messagehandlers.CreateMessageEventHandler
import com.alongo.discordbot.domain.messagehandlers.MessageHandlerProvider
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
