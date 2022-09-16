package com.alongo.discordbot.di.data.audio

import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import dagger.Module
import dagger.Provides

@Module
class LavaPlayerAudioPlayerManagerModule {
    @Provides
    fun provideAudioPlayerManager(): DefaultAudioPlayerManager = DefaultAudioPlayerManager()
}
