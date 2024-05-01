package com.alongo.discordbot.domain.message_handlers.audio

import com.alongo.discordbot.data.audio.LavaPlayerClient
import com.alongo.discordbot.data.datasource.PlayerStorage
import com.alongo.discordbot.domain.message_handlers.BaseMessageHandler
import dev.kord.core.entity.ReactionEmoji
import dev.kord.core.event.message.MessageCreateEvent
import kotlinx.coroutines.delay
import javax.inject.Inject

class ResumeAudioMessageHandler @Inject constructor(
    private val lavaPlayerClient: LavaPlayerClient,
    private val playerStorage: PlayerStorage,
) :
    BaseMessageHandler() {
    override suspend fun handle(command: String, event: MessageCreateEvent) {
        val voiceChannelId = event.member?.getVoiceStateOrNull()?.channelId ?: return
        val player = playerStorage[voiceChannelId]
        lavaPlayerClient.resumeTrack(player)
        val resumeEmoji = ReactionEmoji.Unicode("▶️")
        event.message.addReaction(resumeEmoji)
        delay(3000L)
        event.message.delete()
    }
}
