package com.alongo.discordbot.di.data.datasource.player

import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers
import dagger.Module
import dagger.Provides

@Module
class LavaPlayerModule {
    @Provides
    fun provideLavaPlayer(): DefaultAudioPlayerManager = DefaultAudioPlayerManager().apply {
        // to use YouTube, we tell LavaPlayer to use remote sources, like YouTube.
        AudioSourceManagers.registerRemoteSources(this)
    }
}
