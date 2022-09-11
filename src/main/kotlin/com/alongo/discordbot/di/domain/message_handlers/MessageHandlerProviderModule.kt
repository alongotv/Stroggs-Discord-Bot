package com.alongo.discordbot.di.domain.message_handlers

import com.alongo.discordbot.domain.message_handlers.MessageHandlerProvider
import com.alongo.discordbot.domain.message_handlers.audio.AudioMessageHandler
import com.alongo.discordbot.domain.message_handlers.misc.HelpMessageHandler
import com.alongo.discordbot.domain.message_handlers.misc.KekMessageHandler
import com.alongo.discordbot.domain.message_handlers.qr.QrDecodeMessageHandler
import com.alongo.discordbot.domain.message_handlers.qr.QrEncodeMessageHandler
import dagger.Module
import dagger.Provides

@Module
class MessageHandlerProviderModule {
    @Provides
    fun provideMessageHandlerProvider(
        kekMessageHandler: KekMessageHandler,
        qrEncodeMessageHandler: QrEncodeMessageHandler,
        qrDecodeMessageHandler: QrDecodeMessageHandler,
        helpMessageHandler: HelpMessageHandler,
        audioMessageHandler: AudioMessageHandler
    ) = MessageHandlerProvider(
        kekMessageHandler,
        qrEncodeMessageHandler,
        qrDecodeMessageHandler,
        helpMessageHandler,
        audioMessageHandler
    )
}