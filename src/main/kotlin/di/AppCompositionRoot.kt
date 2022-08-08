package di

import data.MessageCreateEventTransmitter
import domain.MessageHandlerFactory
import domain.usecase.GenerateQrCodeUseCase
import domain.usecase.RemoveLocalFileUseCase
import utils.QrCodeGenerator

class AppCompositionRoot private constructor() {

    val messageCreateEventTransmitter = MessageCreateEventTransmitter()

    private val qrCodeGenerator = QrCodeGenerator()
    private val generateQrCodeUseCase = GenerateQrCodeUseCase(qrCodeGenerator)
    private val removeLocalFileUseCase = RemoveLocalFileUseCase()

    val messageHandlerFactory =
        MessageHandlerFactory(messageCreateEventTransmitter, generateQrCodeUseCase, removeLocalFileUseCase)

    companion object {
        lateinit var self: AppCompositionRoot

        fun getInstance(): AppCompositionRoot {
            if (!::self.isInitialized)
                self = AppCompositionRoot()
            return self
        }
    }
}
