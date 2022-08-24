package com.alongo.discordbot.di.domain.message_handlers

import com.alongo.discordbot.data.MessageCreateEventTransmitter
import com.alongo.discordbot.di.QrModule
import com.alongo.discordbot.domain.message_handlers.MessageHandlerFactory
import com.alongo.discordbot.domain.scenario.GenerateQrCodeFromTextScenario
import com.alongo.discordbot.domain.usecase.ResolveQrCodeUseCase
import dagger.Module
import dagger.Provides

@Module(includes = [QrModule::class])
class MessageHandlerFactoryModule {
    @Provides
    fun provideMessageHandlerFactory(
        messageCreateEventTransmitter: MessageCreateEventTransmitter,
        resolveQrCodeUseCase: ResolveQrCodeUseCase,
        generateQrCodeFromTextScenario: GenerateQrCodeFromTextScenario
    ) =
        MessageHandlerFactory(messageCreateEventTransmitter, generateQrCodeFromTextScenario, resolveQrCodeUseCase)
}
