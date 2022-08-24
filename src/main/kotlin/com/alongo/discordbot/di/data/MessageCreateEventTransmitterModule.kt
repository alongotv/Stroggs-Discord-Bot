package com.alongo.discordbot.di.data

import com.alongo.discordbot.data.MessageCreateEventTransmitter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MessageCreateEventTransmitterModule {
    @Provides
    @Singleton
    fun provideMessageCreateEventTransmitter() = MessageCreateEventTransmitter()
}
