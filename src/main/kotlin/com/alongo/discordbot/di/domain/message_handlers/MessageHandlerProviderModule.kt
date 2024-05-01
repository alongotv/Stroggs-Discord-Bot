package com.alongo.discordbot.di.domain.message_handlers

import com.alongo.discordbot.domain.messagehandlers.MessageHandlerProvider
import com.alongo.discordbot.domain.messagehandlers.audio.PauseAudioMessageHandler
import com.alongo.discordbot.domain.messagehandlers.audio.PlayAudioMessageHandler
import com.alongo.discordbot.domain.messagehandlers.audio.ResumeAudioMessageHandler
import com.alongo.discordbot.domain.messagehandlers.audio.StopAudioMessageHandler
import com.alongo.discordbot.domain.messagehandlers.misc.HelpMessageHandler
import com.alongo.discordbot.domain.messagehandlers.misc.KekMessageHandler
import com.alongo.discordbot.domain.messagehandlers.qr.QrDecodeMessageHandler
import com.alongo.discordbot.domain.messagehandlers.qr.QrEncodeMessageHandler
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
