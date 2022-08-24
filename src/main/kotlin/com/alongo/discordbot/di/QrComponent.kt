package com.alongo.discordbot.di

import dagger.Component

@Component(modules = [QrModule::class])
interface QrComponent {
    @Component.Builder
    interface Builder {
        fun module(qrModule: QrModule)
        fun build(): QrComponent
    }
}
