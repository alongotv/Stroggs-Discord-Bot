package com.alongo.discordbot.data.datasource

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import dev.kord.common.entity.Snowflake

class PlayerStorage(private val lavaPlayerManager: DefaultAudioPlayerManager) {
    private val players = hashMapOf<Snowflake, AudioPlayer>()

    operator fun get(memberVoiceChannelId: Snowflake): AudioPlayer {
        val playerByKey = players[memberVoiceChannelId]
        return if (playerByKey == null) {
            val audioPlayer = lavaPlayerManager.createPlayer()
            players[memberVoiceChannelId] = audioPlayer
            audioPlayer
        } else {
            playerByKey
        }
    }
}
