package com.alongo.discordbot.feature.command

sealed class Command(vararg val keywords: String, val requireCommandMarker: Boolean = true) {
    object KEK : Command("kek", "кек", requireCommandMarker = false)
    object AUDIO {
        object PLAY : Command("play")
        object RESUME: Command("resume")
        object PAUSE: Command("pause")
        object STOP: Command("stop")
    }

    object QR {
        object ENCODE: Command("qrencode")
        object DECODE: Command("qrdecode")
    }
}
