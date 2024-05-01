package com.alongo.discordbot.feature.command

abstract class FeatureCommandProvider {
    abstract fun get(): Collection<Command>
}
