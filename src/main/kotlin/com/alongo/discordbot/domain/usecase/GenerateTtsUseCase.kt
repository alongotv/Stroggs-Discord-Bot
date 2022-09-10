package com.alongo.discordbot.domain.usecase

import com.alongo.discordbot.data.datasource.networking.VoiceApi

class GenerateTtsUseCase(private val voiceApi: VoiceApi) {
    suspend fun invoke(text: String): String {
        return voiceApi.generateSpeech(text)
    }
}
