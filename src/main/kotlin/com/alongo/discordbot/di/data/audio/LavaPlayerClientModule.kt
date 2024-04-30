package com.alongo.discordbot.di.data.audio

import com.alongo.discordbot.data.audio.LavaPlayerClient
import com.alongo.discordbot.data.datasource.PlayerStorage
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LavaPlayerClientModule {
    @Provides
    @Singleton
    fun providePlayerStorage(defaultAudioPlayerManager: DefaultAudioPlayerManager) =
        PlayerStorage(defaultAudioPlayerManager)

    @Provides
    @Singleton
    fun provideLavaPlayerClient(defaultAudioPlayerManager: DefaultAudioPlayerManager) =
        LavaPlayerClient(defaultAudioPlayerManager)
}
