package com.alongo.discordbot.di.domain.usecase

import com.alongo.discordbot.domain.usecase.ResolveQrCodeUseCase
import com.alongo.discordbot.utils.qr.QrCodeResolver
import dagger.Module
import dagger.Provides

@Module
class ResolveQrCodeUseCaseModule {
    @Provides
    fun provideResolveQrCodeUseCase(qrCodeResolver: QrCodeResolver) = ResolveQrCodeUseCase(qrCodeResolver)
}
