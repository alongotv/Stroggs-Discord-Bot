package com.alongo.discordbot.domain.message_handlers.audio

import com.alongo.discordbot.data.audio.KordAudioConnectionClient
import com.alongo.discordbot.data.audio.LavaPlayerClient
import com.alongo.discordbot.domain.exceptions.EndOfTrackQueueException
import com.alongo.discordbot.domain.message_handlers.BaseMessageHandler
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import dev.kord.core.behavior.reply
import dev.kord.core.event.message.MessageCreateEvent
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class PlayAudioMessageHandler @Inject constructor(
    private val kordAudioConnectionClient: KordAudioConnectionClient,
    private val lavaPlayerClient: LavaPlayerClient
) : BaseMessageHandler() {

    override suspend fun handle(command: String, event: MessageCreateEvent) {
        with(event) {
            val voiceChannelId = member?.getVoiceStateOrNull()?.channelId ?: return
            val query = "ytsearch: $command"
            messageHandlerScope.launch {
                lavaPlayerClient.errors.collect {
                    when (it) {
                        is EndOfTrackQueueException -> {
                            kordAudioConnectionClient.disconnect(guildId!!)
                        }
                        is FriendlyException -> {
                            message.reply {
                                content = "There was an error during loading the track."
                                println(it.message)
                            }
                        }
                        is IllegalArgumentException -> {
                            message.reply {
                                content = "Track with provided description not found."
                            }
                        }
                        else -> {

                        }
                    }
                }
            }

            val player = lavaPlayerClient.playTrack(voiceChannelId, query)
            kordAudioConnectionClient.connect(guildId!!, voiceChannelId, player)
            message.reply {
                content = "Playing track: ${player.playingTrack.info.title}"
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
                it.resumeWithException(IllegalArgumentException("No matches to the query"))
            }

            override fun loadFailed(exception: FriendlyException?) {
                if (exception != null) {
                    it.resumeWithException(exception)
                }
            }
        })
    }

    player.playTrack(track)

    return track
}