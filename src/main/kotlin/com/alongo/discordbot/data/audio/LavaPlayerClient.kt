package com.alongo.discordbot.data.audio

import com.alongo.discordbot.domain.exceptions.EndOfTrackQueueException
import com.alongo.discordbot.domain.message_handlers.audio.playTrack
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import com.sedmelluq.discord.lavaplayer.player.event.AudioEvent
import com.sedmelluq.discord.lavaplayer.player.event.TrackEndEvent
import dev.kord.common.entity.Snowflake
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.*
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
        playerScope.coroutineContext.cancelChildren()
        val player = if (!players.containsKey(memberVoiceChannelId)) {
            val audioPlayer = lavaPlayerManager.createPlayer()
            players[memberVoiceChannelId] = audioPlayer
            audioPlayer
        } else {
            players[memberVoiceChannelId]!!
        }

        player.listenForEvents()
            .filter { it is TrackEndEvent }
            .onEach {
                stopTrack(memberVoiceChannelId)
                // Propagate cancellation of keeping voice channel alive
                playerScope.launch {
                    _errors.emit(EndOfTrackQueueException())
                }
            }.launchIn(playerScope)

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
            player.destroy()
            players.remove(memberVoiceChannelId)
        }
    }
}


fun AudioPlayer.listenForEvents(): Flow<AudioEvent> =
    callbackFlow {
        val listener: (AudioEvent) -> Unit = { audioEvent ->
            trySendBlocking(audioEvent)
        }
        this@listenForEvents.addListener(listener)
        awaitClose { this@listenForEvents.removeListener(listener) }
    }
