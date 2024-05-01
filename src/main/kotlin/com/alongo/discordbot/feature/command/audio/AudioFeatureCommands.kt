package com.alongo.discordbot.feature.command.audio

import com.alongo.discordbot.feature.command.Command
import com.alongo.discordbot.feature.command.FeatureCommandProvider

object AudioFeatureCommands : FeatureCommandProvider() {
    override fun get(): Collection<Command> =
        setOf(
            Command.AUDIO.PLAY,
            Command.AUDIO.PAUSE,
            Command.AUDIO.RESUME,
            Command.AUDIO.STOP
        )
}
