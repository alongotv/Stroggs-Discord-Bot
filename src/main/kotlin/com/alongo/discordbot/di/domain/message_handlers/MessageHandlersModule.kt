package com.alongo.discordbot.di.domain.message_handlers

import com.alongo.discordbot.data.audio.KordAudioConnectionClient
import com.alongo.discordbot.data.audio.LavaPlayerClient
import com.alongo.discordbot.data.audio.LavaPlayerQueryWrapper
import com.alongo.discordbot.domain.message_handlers.audio.PlayAudioMessageHandler
import com.alongo.discordbot.data.datasource.PlayerStorage
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
    fun provideAudioMessageHandler(
        kordAudioConnectionClient: KordAudioConnectionClient,
        lavaPlayerClient: LavaPlayerClient,
        playerStorage: PlayerStorage,
        lavaPlayerQueryWrapper: LavaPlayerQueryWrapper
    ) = PlayAudioMessageHandler(
        kordAudioConnectionClient = kordAudioConnectionClient,
        lavaPlayerClient = lavaPlayerClient,
        playerStorage = playerStorage,
        lavaPlayerQueryWrapper = lavaPlayerQueryWrapper
    )
}
