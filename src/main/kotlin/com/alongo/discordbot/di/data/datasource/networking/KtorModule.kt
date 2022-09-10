package com.alongo.discordbot.di.data.datasource.networking

import com.alongo.discordbot.di.data.datasource.networking.voice_api.VoiceApiModule
import dagger.Module
import dagger.Provides
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*

@Module(includes = [VoiceApiModule::class])
class KtorModule {
    @Provides
    fun provideKtor() = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.NONE
        }
    }
}
