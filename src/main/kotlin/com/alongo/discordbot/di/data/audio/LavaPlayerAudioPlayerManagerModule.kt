package com.alongo.discordbot.di.data.audio

import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LavaPlayerAudioPlayerManagerModule {
    @Provides
    @Singleton
    fun provideAudioPlayerManager(): DefaultAudioPlayerManager = DefaultAudioPlayerManager().apply {
        // to use YouTube, we tell LavaPlayer to use remote sources, like YouTube.
        AudioSourceManagers.registerRemoteSources(this)
    }
}
