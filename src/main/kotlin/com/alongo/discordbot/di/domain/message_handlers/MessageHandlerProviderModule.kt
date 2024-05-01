package com.alongo.discordbot.di.domain.message_handlers

import com.alongo.discordbot.domain.message_handlers.MessageHandlerProvider
import com.alongo.discordbot.domain.message_handlers.audio.PauseAudioMessageHandler
import com.alongo.discordbot.domain.message_handlers.audio.PlayAudioMessageHandler
import com.alongo.discordbot.domain.message_handlers.audio.ResumeAudioMessageHandler
import com.alongo.discordbot.domain.message_handlers.audio.StopAudioMessageHandler
import com.alongo.discordbot.domain.message_handlers.misc.HelpMessageHandler
import com.alongo.discordbot.domain.message_handlers.misc.KekMessageHandler
import com.alongo.discordbot.domain.message_handlers.qr.QrDecodeMessageHandler
import com.alongo.discordbot.domain.message_handlers.qr.QrEncodeMessageHandler
import dagger.Module
import dagger.Provides

@Module
class MessageHandlerProviderModule {
    @Suppress("LongParameterList")
    @Provides
    fun provideMessageHandlerProvider(
        kekMessageHandler: KekMessageHandler,
        qrEncodeMessageHandler: QrEncodeMessageHandler,
        qrDecodeMessageHandler: QrDecodeMessageHandler,
        helpMessageHandler: HelpMessageHandler,
        playAudioMessageHandler: PlayAudioMessageHandler,
        stopAudioMessageHandler: StopAudioMessageHandler,
        pauseAudioMessageHandler: PauseAudioMessageHandler,
        resumeAudioMessageHandler: ResumeAudioMessageHandler
    ) = MessageHandlerProvider(
        kekMessageHandler,
        qrEncodeMessageHandler,
        qrDecodeMessageHandler,
        helpMessageHandler,
        playAudioMessageHandler,
        stopAudioMessageHandler,
        pauseAudioMessageHandler,
        resumeAudioMessageHandler
    )
}
