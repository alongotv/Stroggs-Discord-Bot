package com.alongo.discordbot.domain.entity.dto.voice

import kotlinx.serialization.Serializable

@Serializable
data class GenerateTextToVoiceResponseDto(
    val Error: Int,
    val Speaker: String,
    val Cached: Int,
    val Text: String,
    val tasktype: String,
    val URL: String,
    val MP3: String
)