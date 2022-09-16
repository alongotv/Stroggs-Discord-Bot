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

    suspend fun playTrack(memberId: Snowflake, query: String): AudioPlayer {
        if (players[memberId] == null) {
            players[memberId] = lavaPlayerManager.createPlayer()
        }
        val player = players[memberId]!!
        lavaPlayerManager.playTrack(query, player)
        return player
    }

    fun resumeTrack(memberId: Snowflake) {
        val player = players[memberId]!!
        player.playTrack(player.playingTrack)
    }

    fun pauseTrack(memberId: Snowflake) {
        val player = players[memberId]!!
        player.stopTrack()
    }

    fun stopTrack(memberId: Snowflake) {
        val player = players[memberId]!!
        player.stopTrack()
        players.remove(memberId)
    }
}
