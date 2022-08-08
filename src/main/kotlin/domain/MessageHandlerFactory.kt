package domain

import data.MessageCreateEventTransmitter
import domain.message_handlers.KekMessageHandler
import domain.message_handlers.QrMessageHandler
import domain.usecase.GenerateQrCodeUseCase
import domain.usecase.RemoveLocalFileUseCase

class MessageHandlerFactory(
    private val messageCreateEventTransmitter: MessageCreateEventTransmitter,
    private val generateQrCodeUseCase: GenerateQrCodeUseCase,
    private val removeLocalQrCodeUseCase: RemoveLocalFileUseCase
) {

    suspend fun generateMessageHandlers() {
        KekMessageHandler(messageCreateEventTransmitter).setup()
        QrMessageHandler(messageCreateEventTransmitter, generateQrCodeUseCase, removeLocalQrCodeUseCase).setup()
    }
}
