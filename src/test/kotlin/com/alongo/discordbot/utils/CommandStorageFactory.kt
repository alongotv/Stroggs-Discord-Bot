package com.alongo.discordbot.utils

import com.alongo.discordbot.feature.command.Command
import com.alongo.discordbot.feature.command.audio.AudioFeatureCommands
import com.alongo.discordbot.feature.command.misc.FunFeatureCommands
import com.alongo.discordbot.feature.command.qr.QrFeatureCommands

object CommandStorageFactory {
    fun create(): List<Command> {
        return FunFeatureCommands.get() +
            QrFeatureCommands.get() +
            AudioFeatureCommands.get()
    }
}
