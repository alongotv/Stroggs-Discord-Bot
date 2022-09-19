package com.alongo.discordbot.domain.message_handlers.audio

import com.alongo.discordbot.data.audio.KordAudioConnectionClient
import com.alongo.discordbot.data.audio.LavaPlayerClient
import com.alongo.discordbot.domain.message_handlers.BaseMessageHandler
import dev.kord.core.event.message.MessageCreateEvent
import javax.inject.Inject

class StopAudioMessageHandler @Inject constructor(
    private val kordAudioConnectionClient: KordAudioConnectionClient,
    private val lavaPlayerClient: LavaPlayerClient,
) : BaseMessageHandler() {
    override suspend fun handle(command: String, event: MessageCreateEvent) {
        event.guildId?.let { kordAudioConnectionClient.disconnect(it) }
        val voiceChannelId = event.member?.getVoiceStateOrNull()?.channelId ?: return
        lavaPlayerClient.stopTrack(voiceChannelId)
    }
}
