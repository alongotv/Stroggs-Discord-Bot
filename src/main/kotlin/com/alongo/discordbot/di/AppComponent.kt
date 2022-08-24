package com.alongo.discordbot.di

import com.alongo.discordbot.Application
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(application: Application)
}
