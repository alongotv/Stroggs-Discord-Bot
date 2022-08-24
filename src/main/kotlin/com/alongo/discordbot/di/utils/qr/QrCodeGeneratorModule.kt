package com.alongo.discordbot.di.utils.qr

import com.alongo.discordbot.utils.qr.QrCodeGenerator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class QrCodeGeneratorModule {
    @Provides
    @Singleton
    fun provideQrCodeGenerator() = QrCodeGenerator()
}
