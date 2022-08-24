package com.alongo.discordbot.di.utils.qr

import com.alongo.discordbot.utils.qr.QrCodeResolver
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class QrCodeResolverModule {
    @Provides
    @Singleton
    fun provideQrCodeResolver() = QrCodeResolver()
}
