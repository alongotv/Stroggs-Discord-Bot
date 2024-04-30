package com.alongo.discordbot.data.audio

import com.alongo.discordbot.domain.message_handlers.audio.playTrack
import com.alongo.discordbot.utils.audio.listenForEvents
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import com.sedmelluq.discord.lavaplayer.player.event.TrackEndEvent
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class LavaPlayerClient @Inject constructor(
    private val lavaPlayerManager: DefaultAudioPlayerManager
) {
    private val playerScope = CoroutineScope(Dispatchers.IO)

    suspend fun playTrack(
        player: AudioPlayer,
        query: String,
        onTrackQueueEnd: suspend () -> Unit = {}
    ): AudioPlayer {
        player.listenForEvents()
            .filter { it is TrackEndEvent && it.endReason == AudioTrackEndReason.FINISHED }
            .onEach {
                onTrackQueueEnd()
                stopTrack(player)
            }.launchIn(playerScope)

        lavaPlayerManager.playTrack(query, player)
        return player
    }

    fun resumeTrack(player: AudioPlayer) {
        player.isPaused = false
    }

    fun pauseTrack(player: AudioPlayer) {
        player.isPaused = true
    }

    fun stopTrack(player: AudioPlayer) {
        player.destroy()
    }
}

