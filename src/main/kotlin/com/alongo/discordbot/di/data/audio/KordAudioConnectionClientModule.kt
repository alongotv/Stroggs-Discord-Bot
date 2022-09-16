package com.alongo.discordbot.di.data.audio

import com.alongo.discordbot.data.audio.KordAudioConnectionClient
import dagger.Module
import dagger.Provides
import dev.kord.core.Kord
import kotlinx.coroutines.Deferred
import javax.inject.Singleton

@Module
class KordAudioConnectionClientModule {
    @Provides
    @Singleton
    fun provideKordAudioConnectionClient(kord: Deferred<Kord>): KordAudioConnectionClient = KordAudioConnectionClient(kord)
}
