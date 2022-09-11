package com.alongo.discordbot.domain.message_handlers

import com.alongo.discordbot.domain.message_handlers.audio.AudioMessageHandler
import com.alongo.discordbot.domain.message_handlers.misc.HelpMessageHandler
import com.alongo.discordbot.domain.message_handlers.misc.KekMessageHandler
import com.alongo.discordbot.domain.message_handlers.qr.QrDecodeMessageHandler
import com.alongo.discordbot.domain.message_handlers.qr.QrEncodeMessageHandler
import javax.inject.Inject

class MessageHandlerProvider @Inject constructor(
    private val kekMessageHandler: KekMessageHandler,
    private val qrEncodeMessageHandler: QrEncodeMessageHandler,
    private val qrDecodeMessageHandler: QrDecodeMessageHandler,
    private val helpMessageHandler: HelpMessageHandler,
    private val audioMessageHandler: AudioMessageHandler
) {
    fun getHandler(type: COMMAND_TYPE): BaseMessageHandler =
        when (type) {
            COMMAND_TYPE.KEK, COMMAND_TYPE.KEK_CYRILLIC -> kekMessageHandler
            COMMAND_TYPE.QR_ENCODE -> qrEncodeMessageHandler
            COMMAND_TYPE.QR_DECODE -> qrDecodeMessageHandler
            COMMAND_TYPE.HELP -> helpMessageHandler
            COMMAND_TYPE.AUDIO_MESSAGE -> audioMessageHandler
        }
}