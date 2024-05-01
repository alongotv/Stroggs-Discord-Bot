package com.alongo.discordbot.domain.message_handlers.audio

import com.alongo.discordbot.data.audio.KordAudioConnectionClient
import com.alongo.discordbot.data.audio.LavaPlayerClient
import com.alongo.discordbot.data.audio.LavaPlayerQueryWrapper
import com.alongo.discordbot.data.datasource.PlayerStorage
import com.alongo.discordbot.domain.message_handlers.BaseMessageHandler
import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import dev.kord.common.entity.Snowflake
import dev.kord.core.behavior.reply
import dev.kord.core.event.message.MessageCreateEvent
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class PlayAudioMessageHandler @Inject constructor(
    private val kordAudioConnectionClient: KordAudioConnectionClient,
    private val lavaPlayerClient: LavaPlayerClient,
    private val playerStorage: PlayerStorage,
    private val lavaPlayerQueryWrapper: LavaPlayerQueryWrapper
) : BaseMessageHandler() {

    override suspend fun handle(command: String, event: MessageCreateEvent) {
        messageHandlerScope.launch {
            with(event) {
                val voiceChannelId = member?.getVoiceStateOrNull()?.channelId ?: return@launch
                val query = lavaPlayerQueryWrapper.wrap(command)
                val player = playerStorage[voiceChannelId]
                try {
                    lavaPlayerClient.playTrack(player, query) {
                        // Just disconnect from the voice channel
                        disconnect(guildId)
                    }
                    kordAudioConnectionClient.connect(guildId!!, voiceChannelId, player)
                    message.reply {
                        content = "Playing track: ${player.playingTrack?.info?.title}"
                    }
                } catch (e: CancellationException) {
                    disconnect(guildId)
                    throw e
                } catch (e: FriendlyException) {
                    message.reply {
                        content = "There was an error during loading the track."
                        println(e.message)
                    }
                    disconnect(guildId)
                } catch (e: IllegalArgumentException) {
                    message.reply {
                        content = "Track with provided description not found."
                    }
                    disconnect(guildId)
                } catch (e: Exception) {
                    println(e.localizedMessage)
                    disconnect(guildId)
                }
            }
        }
    }

    private suspend fun disconnect(guildId: Snowflake?) {
        guildId?.let { kordAudioConnectionClient.disconnect(it) }
    }
}

suspend fun DefaultAudioPlayerManager.playTrack(query: String, player: AudioPlayer): AudioTrack {
    val track = suspendCoroutine<AudioTrack> {
        this.loadItem(
            query,
            object : AudioLoadResultHandler {
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
                    it.resumeWithException(
                        exception ?: FriendlyException(
                            "Unknown cause",
                            FriendlyException.Severity.SUSPICIOUS, null
                        )
                    )
                }
            }
        )
    }

    player.playTrack(track)

    return track
}
