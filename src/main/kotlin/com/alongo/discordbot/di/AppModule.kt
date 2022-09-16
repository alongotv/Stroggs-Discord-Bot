package com.alongo.discordbot.di

import com.alongo.discordbot.di.data.MessageCreateEventTransmitterModule
import com.alongo.discordbot.di.data.audio.KordAudioConnectionClientModule
import com.alongo.discordbot.di.data.audio.LavaPlayerAudioPlayerManagerModule
import com.alongo.discordbot.di.data.audio.LavaPlayerClientModule
import com.alongo.discordbot.di.domain.message_handlers.MessageHandlerFactoryModule
import dagger.Module
import dagger.Provides
import dev.kord.core.Kord
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import javax.inject.Singleton

@Module(
    includes = [MessageCreateEventTransmitterModule::class, MessageHandlerFactoryModule::class, KordAudioConnectionClientModule::class, LavaPlayerAudioPlayerManagerModule::class, LavaPlayerClientModule::class]
)
class AppModule {
    @Provides
    @Singleton
    fun provideKord(): Deferred<Kord> {
        val deferred = CompletableDeferred<Kord>()
        runBlocking {
            val token = System.getenv("STROGG_DISCORD_BOT_KEY")
            deferred.complete(Kord(token))
        }
        return deferred
    }
}
