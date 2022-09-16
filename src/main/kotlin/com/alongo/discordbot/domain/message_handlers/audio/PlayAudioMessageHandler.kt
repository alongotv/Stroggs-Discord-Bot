package com.alongo.discordbot.domain.message_handlers.audio

import com.alongo.discordbot.data.audio.KordAudioConnectionClient
import com.alongo.discordbot.data.audio.LavaPlayerClient
import com.alongo.discordbot.domain.message_handlers.BaseMessageHandler
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import dev.kord.core.behavior.reply
import dev.kord.core.event.message.MessageCreateEvent
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class PlayAudioMessageHandler @Inject constructor(
    private val kordAudioConnectionClient: KordAudioConnectionClient,
    private val lavaPlayerClient: LavaPlayerClient
) : BaseMessageHandler() {

    override suspend fun handle(command: String, event: MessageCreateEvent) {
        with(event) {
            val voiceChannelId = member?.getVoiceState()?.channelId ?: return
            val query = "ytsearch: $command"
            val player = lavaPlayerClient.playTrack(voiceChannelId, query)
            kordAudioConnectionClient.connect(guildId!!, voiceChannelId, player.provide().data)

            message.reply {
                content = "playing track: ${player.playingTrack.info.title}"
            }
        }
    }
}

// lavaplayer isn't super kotlin-friendly, so we'll make it nicer to work with
suspend fun DefaultAudioPlayerManager.playTrack(query: String, player: AudioPlayer): AudioTrack {
    val track = suspendCoroutine<AudioTrack> {
        this.loadItem(query, object : AudioLoadResultHandler {
            override fun trackLoaded(track: AudioTrack) {
                it.resume(track)
            }

            override fun playlistLoaded(playlist: AudioPlaylist) {
                it.resume(playlist.tracks.first())
            }

            override fun noMatches() {
//                TODO()
            }

            override fun loadFailed(exception: FriendlyException?) {
//                TODO()
            }
        })
    }

    player.playTrack(track)

    return track
}