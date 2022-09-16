package com.alongo.discordbot.di.data.audio

import com.alongo.discordbot.data.audio.LavaPlayerClient
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager
import dagger.Module
import dagger.Provides

@Module
class LavaPlayerClientModule {
    @Provides
    fun provideLavaPlayerClient(defaultAudioPlayerManager: DefaultAudioPlayerManager) =
        LavaPlayerClient(defaultAudioPlayerManager)
}
