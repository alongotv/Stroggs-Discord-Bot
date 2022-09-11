package com.alongo.discordbot.di.domain.message_handlers

import com.alongo.discordbot.domain.message_handlers.audio.AudioMessageHandler
import com.alongo.discordbot.domain.message_handlers.misc.HelpMessageHandler
import com.alongo.discordbot.domain.message_handlers.misc.KekMessageHandler
import com.alongo.discordbot.domain.message_handlers.qr.QrDecodeMessageHandler
import com.alongo.discordbot.domain.message_handlers.qr.QrEncodeMessageHandler
import com.alongo.discordbot.domain.scenario.GenerateQrCodeFromTextScenario
import com.alongo.discordbot.domain.usecase.ResolveQrCodeUseCase
import dagger.Module
import dagger.Provides

@Module
class MessageHandlersModule {
    @Provides
    fun provideQrDecodeMessageHandler(resolveQrCodeUseCase: ResolveQrCodeUseCase) =
        QrDecodeMessageHandler(resolveQrCodeUseCase)
    @Provides
    fun provideQrEncodeMessageHandler(generateQrCodeFromTextScenario: GenerateQrCodeFromTextScenario) =
        QrEncodeMessageHandler(generateQrCodeFromTextScenario)
    @Provides
    fun provideKekMessageHandler() = KekMessageHandler()
    @Provides
    fun provideHelpMessageHandler() = HelpMessageHandler()
    @Provides
    fun provideAudioMessageHandler() = AudioMessageHandler()
}
