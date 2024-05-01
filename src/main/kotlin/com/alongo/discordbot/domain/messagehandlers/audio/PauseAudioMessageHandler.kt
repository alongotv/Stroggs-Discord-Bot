package com.alongo.discordbot.domain.messagehandlers.audio

import com.alongo.discordbot.data.audio.LavaPlayerClient
import com.alongo.discordbot.data.datasource.PlayerStorage
import com.alongo.discordbot.domain.messagehandlers.BaseMessageHandler
import dev.kord.core.entity.ReactionEmoji
import dev.kord.core.event.message.MessageCreateEvent
import kotlinx.coroutines.delay
import javax.inject.Inject

class PauseAudioMessageHandler @Inject constructor(
    private val lavaPlayerClient: LavaPlayerClient,
    private val playerStorage: PlayerStorage,
) : BaseMessageHandler() {
    override suspend fun handle(command: String, event: MessageCreateEvent) {
        val voiceChannelId = event.member?.getVoiceStateOrNull()?.channelId ?: return
        val player = playerStorage[voiceChannelId]
        lavaPlayerClient.pauseTrack(player)
        val pauseEmoji = ReactionEmoji.Unicode("⏸️")
        event.message.addReaction(pauseEmoji)
        delay(REMOVE_REACTION_DELAY)
        event.message.delete()
    }
}

private const val REMOVE_REACTION_DELAY = 3000L
