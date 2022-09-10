package com.alongo.discordbot.domain.entity.dto.voice

import kotlinx.serialization.Serializable

@Serializable
data class GenerateTextToVoiceRequestDto(val msg: String, val lang: String, val source: String)
