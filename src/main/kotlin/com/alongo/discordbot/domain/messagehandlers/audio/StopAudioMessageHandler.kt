package com.alongo.discordbot.domain.messagehandlers.audio

import com.alongo.discordbot.data.audio.KordAudioConnectionClient
import com.alongo.discordbot.data.audio.LavaPlayerClient
import com.alongo.discordbot.data.datasource.PlayerStorage
import com.alongo.discordbot.domain.messagehandlers.BaseMessageHandler
import dev.kord.core.entity.ReactionEmoji
import dev.kord.core.event.message.MessageCreateEvent
import kotlinx.coroutines.delay
import javax.inject.Inject

class StopAudioMessageHandler @Inject constructor(
    private val kordAudioConnectionClient: KordAudioConnectionClient,
    private val lavaPlayerClient: LavaPlayerClient,
    private val playerStorage: PlayerStorage,
) : BaseMessageHandler() {
    override suspend fun handle(command: String, event: MessageCreateEvent) {
        event.guildId?.let { kordAudioConnectionClient.disconnect(it) }
        val voiceChannelId = event.member?.getVoiceStateOrNull()?.channelId ?: return
        val player = playerStorage[voiceChannelId]
        lavaPlayerClient.stopTrack(player)
        val stopEmoji = ReactionEmoji.Unicode("\uD83D\uDED1")
        event.message.addReaction(stopEmoji)
        delay(3000L)
        event.message.delete()
    }
}
