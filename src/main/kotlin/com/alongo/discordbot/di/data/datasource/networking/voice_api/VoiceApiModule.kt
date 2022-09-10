package com.alongo.discordbot.di.data.datasource.networking.voice_api

import com.alongo.discordbot.data.datasource.networking.VoiceApi
import com.alongo.discordbot.data.datasource.networking.VoiceApiImpl
import dagger.Binds
import dagger.Module

@Module
abstract class VoiceApiModule {
    @Binds
    abstract fun bindVoiceApi(voiceApiImpl: VoiceApiImpl): VoiceApi
}