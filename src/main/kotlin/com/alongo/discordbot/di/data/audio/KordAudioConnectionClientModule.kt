package com.alongo.discordbot.di.data.audio

import com.alongo.discordbot.data.audio.KordAudioConnectionClient
import dagger.Module
import dagger.Provides
import dev.kord.core.Kord
import kotlinx.coroutines.Deferred

@Module
class KordAudioConnectionClientModule {
    @Provides
    fun provideKordAudioConnectionClient(kord: Deferred<Kord>): KordAudioConnectionClient = KordAudioConnectionClient(kord)
}
