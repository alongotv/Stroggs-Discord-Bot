package com.alongo.discordbot.di.domain.scenario

import com.alongo.discordbot.domain.scenario.GenerateQrCodeFromTextScenario
import com.alongo.discordbot.domain.usecase.GenerateQrCodeUseCase
import dagger.Module
import dagger.Provides

@Module
class GenerateQrCodeFromTextScenarioModule {
    @Provides
    fun provideGenerateQrCodeFromTextScenario(generateQrCodeUseCase: GenerateQrCodeUseCase) =
        GenerateQrCodeFromTextScenario(generateQrCodeUseCase)
}
