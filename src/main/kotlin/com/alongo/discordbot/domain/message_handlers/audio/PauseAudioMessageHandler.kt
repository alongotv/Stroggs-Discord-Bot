package com.alongo.discordbot.domain.message_handlers.audio

import com.alongo.discordbot.data.audio.LavaPlayerClient
import com.alongo.discordbot.domain.message_handlers.BaseMessageHandler
import dev.kord.core.event.message.MessageCreateEvent
import javax.inject.Inject

class PauseAudioMessageHandler @Inject constructor(private val lavaPlayerClient: LavaPlayerClient) :
    BaseMessageHandler() {
    override suspend fun handle(command: String, event: MessageCreateEvent) {
        val voiceChannelId = event.member?.getVoiceState()?.channelId ?: return
        lavaPlayerClient.pauseTrack(voiceChannelId)
    }
}
