package com.alongo.discordbot.di

import com.alongo.discordbot.di.domain.scenario.GenerateQrCodeFromTextScenarioModule
import com.alongo.discordbot.di.domain.usecase.ResolveQrCodeUseCaseModule
import com.alongo.discordbot.di.utils.qr.QrCodeGeneratorModule
import com.alongo.discordbot.di.utils.qr.QrCodeResolverModule
import com.alongo.discordbot.domain.usecase.GenerateQrCodeUseCase
import com.alongo.discordbot.utils.qr.QrCodeGenerator
import dagger.Module
import dagger.Provides

@Module(includes = [QrCodeResolverModule::class, QrCodeGeneratorModule::class, GenerateQrCodeFromTextScenarioModule::class, ResolveQrCodeUseCaseModule::class])
class QrModule {
    @Provides
    fun provideGenerateQrCodeUseCase(qrCodeGenerator: QrCodeGenerator): GenerateQrCodeUseCase = GenerateQrCodeUseCase(qrCodeGenerator)
}
