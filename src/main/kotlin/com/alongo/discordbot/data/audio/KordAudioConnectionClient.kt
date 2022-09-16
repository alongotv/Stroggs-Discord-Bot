package com.alongo.discordbot.data.audio

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import dev.kord.common.annotation.KordVoice
import dev.kord.common.entity.Snowflake
import dev.kord.core.Kord
import dev.kord.core.behavior.channel.connect
import dev.kord.core.entity.channel.VoiceChannel
import dev.kord.voice.AudioFrame
import dev.kord.voice.VoiceConnection
import kotlinx.coroutines.Deferred
import javax.inject.Inject

// Class for tracking current audio connections
class KordAudioConnectionClient @Inject constructor(private val kord: Deferred<Kord>) {

    // Live connection storage
    @OptIn(KordVoice::class)
    private val connections: MutableMap<Snowflake, VoiceConnection> = mutableMapOf()

    @OptIn(KordVoice::class)
    suspend fun connect(guildId: Snowflake, memberVoiceChannelId: Snowflake, audioPlayer: AudioPlayer) {
        // Discord only allows one voice connection per guild
        // Check if there is an active voice connection in specific server
        if (connections.contains(guildId)) {
            connections.remove(guildId)!!.shutdown()
        }
        // The channel id current user is connected to
        val voiceChannel = kord.await().getChannelOf<VoiceChannel>(id = memberVoiceChannelId)!!

        val connection = voiceChannel.connect {
            // the audio provider should provide frames of audio
            audioProvider { AudioFrame.fromData(audioPlayer.provide()?.data) }
        }
        connections[guildId] = connection
    }

    @OptIn(KordVoice::class)
    suspend fun disconnect(guildId: Snowflake) {
        connections[guildId]?.shutdown()
    }
}
