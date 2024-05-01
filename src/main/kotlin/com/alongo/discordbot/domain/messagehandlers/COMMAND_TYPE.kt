package com.alongo.discordbot.domain.messagehandlers

enum class COMMAND_TYPE(val command: String) {
    KEK("kek"),
    KEK_CYRILLIC("кек"),
    QR_ENCODE("!qrencode"),
    QR_DECODE("!qrdecode"),
    HELP("!help"),
    AUDIO_PLAY("!play"),
    AUDIO_RESUME("!resume"),
    AUDIO_PAUSE("!pause"),
    AUDIO_STOP("!stop")
}
