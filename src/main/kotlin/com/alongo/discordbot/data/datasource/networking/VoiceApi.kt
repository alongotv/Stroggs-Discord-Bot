package com.alongo.discordbot.data.datasource.networking

interface VoiceApi {
    suspend fun generateSpeech(content: String): String
}