package com.alongo.discordbot.data.audio

import com.alongo.discordbot.domain.exceptions.EndOfTrackQueueException
import com.alongo.discordbot.domain.message_handlers.audio.playTrack
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import com.sedmelluq.discord.lavaplayer.player.event.TrackEndEvent
import dev.kord.common.entity.Snowflake
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LavaPlayerClient @Inject constructor(
    private val lavaPlayerManager: DefaultAudioPlayerManager
) {
    private val playerScope = CoroutineScope(Dispatchers.IO)
    private val players = HashMap<Snowflake, AudioPlayer>()
    private val _errors = MutableSharedFlow<Exception>()
    val errors: SharedFlow<Exception> = _errors

    suspend fun playTrack(memberVoiceChannelId: Snowflake, query: String): AudioPlayer {
        val player = if (!players.containsKey(memberVoiceChannelId)) {
            val audioPlayer = lavaPlayerManager.createPlayer()
            players[memberVoiceChannelId] = audioPlayer
            audioPlayer
        } else {
            players[memberVoiceChannelId]!!
        }

        player.addListener {
            when (it) {
                is TrackEndEvent -> {
                    stopTrack(memberVoiceChannelId)
                    // Propagate cancellation of keeping voice channel alive
                    playerScope.launch {
                        _errors.emit(EndOfTrackQueueException())
                    }
                }
            }
        }

        try {
            lavaPlayerManager.playTrack(query, player)
        } catch (e: Exception) {
            _errors.emit(e)
        }
        return player
    }

    fun resumeTrack(memberVoiceChannelId: Snowflake) {
        val player = players[memberVoiceChannelId]
        if (player != null) {
            player.isPaused = false
        }
    }

    fun pauseTrack(memberVoiceChannelId: Snowflake) {
        val player = players[memberVoiceChannelId]
        if (player != null) {
            player.isPaused = true
        }
    }

    fun stopTrack(memberVoiceChannelId: Snowflake) {
        val player = players[memberVoiceChannelId]
        player?.let {
            player.stopTrack()
            players.remove(memberVoiceChannelId)
        }
    }
}
