package com.alongo.discordbot.feature.command.`fun`

import com.alongo.discordbot.feature.command.Command
import com.alongo.discordbot.feature.command.FeatureCommandProvider

object FunFeatureCommands : FeatureCommandProvider() {
    override fun get(): Collection<Command> = setOf(Command.KEK)
}
