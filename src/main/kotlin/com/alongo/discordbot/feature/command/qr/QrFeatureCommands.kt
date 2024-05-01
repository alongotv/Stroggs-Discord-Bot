package com.alongo.discordbot.feature.command.qr

import com.alongo.discordbot.feature.command.Command
import com.alongo.discordbot.feature.command.FeatureCommandProvider

object QrFeatureCommands: FeatureCommandProvider() {
    override fun get(): Collection<Command> = setOf(
        Command.QR.ENCODE,
        Command.QR.DECODE
    )
}
