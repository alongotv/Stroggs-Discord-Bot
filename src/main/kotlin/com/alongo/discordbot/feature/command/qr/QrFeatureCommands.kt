package com.alongo.discordbot.feature.command.qr

import com.alongo.discordbot.feature.command.Command

object QrFeatureCommands {
    fun create(): Collection<Command> = setOf(
        Command.QR.ENCODE,
        Command.QR.DECODE
    )
}
