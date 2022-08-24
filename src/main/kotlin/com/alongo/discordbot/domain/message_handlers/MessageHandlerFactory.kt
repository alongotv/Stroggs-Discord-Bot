package com.alongo.discordbot.domain.message_handlers

import com.alongo.discordbot.data.MessageCreateEventTransmitter
import com.alongo.discordbot.domain.message_handlers.audio.AudioMessageHandler
import com.alongo.discordbot.domain.message_handlers.misc.HelpMessageHandler
import com.alongo.discordbot.domain.message_handlers.misc.KekMessageHandler
import com.alongo.discordbot.domain.message_handlers.qr.QrDecodeMessageHandler
import com.alongo.discordbot.domain.message_handlers.qr.QrEncodeMessageHandler
import com.alongo.discordbot.domain.scenario.GenerateQrCodeFromTextScenario
import com.alongo.discordbot.domain.usecase.ResolveQrCodeUseCase

class MessageHandlerFactory(
    private val messageCreateEventTransmitter: MessageCreateEventTransmitter,
    private val generateQrCodeFromTextScenario: GenerateQrCodeFromTextScenario,
    private val resolveQrCodeUseCase: ResolveQrCodeUseCase
) {

    fun generateMessageHandlers() {
        KekMessageHandler(messageCreateEventTransmitter).setup()
        QrEncodeMessageHandler(messageCreateEventTransmitter, generateQrCodeFromTextScenario).setup()
        QrDecodeMessageHandler(messageCreateEventTransmitter, resolveQrCodeUseCase).setup()
        HelpMessageHandler(messageCreateEventTransmitter).setup()
        AudioMessageHandler(messageCreateEventTransmitter).setup()
    }
}
