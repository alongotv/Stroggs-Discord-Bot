package com.alongo.discordbot.data.audio

import com.alongo.discordbot.domain.message_handlers.audio.playTrack
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import dev.kord.common.entity.Snowflake
import javax.inject.Inject

class LavaPlayerClient @Inject constructor(
    private val lavaPlayerManager: DefaultAudioPlayerManager
) {
    private val players = HashMap<Snowflake, AudioPlayer>()

    suspend fun playTrack(memberVoiceChannelId: Snowflake, query: String): AudioPlayer {
        if (players[memberVoiceChannelId] == null) {
            players[memberVoiceChannelId] = lavaPlayerManager.createPlayer()
        }
        val player = players[memberVoiceChannelId]!!
        lavaPlayerManager.playTrack(query, player)
        return player
    }

    fun resumeTrack(memberVoiceChannelId: Snowflake) {
        val player = players[memberVoiceChannelId]!!
        player.isPaused = false
    }

    fun pauseTrack(memberVoiceChannelId: Snowflake) {
        val player = players[memberVoiceChannelId]!!
        player.isPaused = true
    }

    fun stopTrack(memberVoiceChannelId: Snowflake) {
        val player = players[memberVoiceChannelId]!!
        player.stopTrack()
        players.remove(memberVoiceChannelId)
    }
}
