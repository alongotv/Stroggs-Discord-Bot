package com.alongo.discordbot.utils.audio

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer
import com.sedmelluq.discord.lavaplayer.player.event.AudioEvent
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

fun AudioPlayer.listenForEvents(): Flow<AudioEvent> =
    callbackFlow {
        val listener: (AudioEvent) -> Unit = { audioEvent ->
            trySendBlocking(audioEvent)
        }
        this@listenForEvents.addListener(listener)
        awaitClose { this@listenForEvents.removeListener(listener) }
    }