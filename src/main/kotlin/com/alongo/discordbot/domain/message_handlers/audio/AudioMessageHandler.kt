package com.alongo.discordbot.domain.message_handlers.audio

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist
import com.sedmelluq.discord.lavaplayer.track.AudioTrack
import com.alongo.discordbot.data.MessageCreateEventTransmitter
import dev.kord.common.annotation.KordVoice
import dev.kord.common.entity.Snowflake
import dev.kord.core.behavior.channel.connect
import dev.kord.core.behavior.reply
import dev.kord.core.entity.channel.VoiceChannel
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.voice.AudioFrame
import dev.kord.voice.VoiceConnection
import com.alongo.discordbot.domain.message_handlers.BaseMessageHandler
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AudioMessageHandler(val messageCreateEventTransmitter: MessageCreateEventTransmitter) :
    BaseMessageHandler(messageCreateEventTransmitter) {
    override val predicate: (MessageCreateEvent) -> Boolean
        get() = { it.message.content.startsWith("!play") || it.message.content.startsWith("!stop")}

    // here we keep track of active voice connections
    @OptIn(KordVoice::class)
    val connections: MutableMap<Snowflake, VoiceConnection> = mutableMapOf()

    private val lavaPlayerManager = DefaultAudioPlayerManager().apply {
        // to use YouTube, we tell LavaPlayer to use remote sources, like YouTube.
        AudioSourceManagers.registerRemoteSources(this)
    }

    @OptIn(KordVoice::class)
    override suspend fun handle(event: MessageCreateEvent) {
        with(event) {
            if(message.content.startsWith("!stop")) {
                connections.forEach { (t, u) ->
                    u.shutdown()
                }
                return
            }

            val channelId = member?.getVoiceState()?.channelId ?: return
            val voiceChannel = kord.getChannelOf<VoiceChannel>(id = channelId)!!

            // lets close the old connection if there is one
            if (connections.contains(guildId)) {
                connections.remove(guildId)!!.shutdown()
            }

            // our lavaplayer audio player which will provide frames of audio
            val player = lavaPlayerManager.createPlayer()

            // lavaplayer uses ytsearch: as an identifier to search for YouTube
            val query = "ytsearch: ${message.content.removePrefix("!play")}"

            val track = lavaPlayerManager.playTrack(query, player)

            // here we actually connect to the voice channel

            val connection = voiceChannel.connect {
                // the audio provider should provide frames of audio
                audioProvider { AudioFrame.fromData(player.provide()?.data) }
            }

            connections[guildId!!] = connection

            message.reply {
                content = "playing track: ${track.info.title}"
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