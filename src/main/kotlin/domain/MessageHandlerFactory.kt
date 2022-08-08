package domain

import data.MessageCreateEventTransmitter
import domain.message_handlers.KekMessageHandler
import domain.message_handlers.QrMessageHandler
import domain.scenario.GenerateQrCodeFromTextScenario
import domain.usecase.ResolveQrCodeUseCase

class MessageHandlerFactory(
    private val messageCreateEventTransmitter: MessageCreateEventTransmitter,
    private val generateQrCodeFromTextScenario: GenerateQrCodeFromTextScenario,
    private val resolveQrCodeUseCase: ResolveQrCodeUseCase
) {

    suspend fun generateMessageHandlers() {
        KekMessageHandler(messageCreateEventTransmitter).setup()
        QrMessageHandler(messageCreateEventTransmitter, generateQrCodeFromTextScenario, resolveQrCodeUseCase).setup()
    }
}
