package di

import data.MessageCreateEventTransmitter
import domain.message_handlers.MessageHandlerFactory
import domain.scenario.GenerateQrCodeFromTextScenario
import domain.usecase.GenerateQrCodeUseCase
import domain.usecase.RemoveLocalFileUseCase
import domain.usecase.ResolveQrCodeUseCase
import utils.qr.QrCodeGenerator
import utils.qr.QrCodeResolver

class AppCompositionRoot private constructor() {

    val messageCreateEventTransmitter = MessageCreateEventTransmitter()

    private val qrCodeGenerator by lazy { QrCodeGenerator() }
    private val qrCodeResolver by lazy { QrCodeResolver() }
    private val generateQrCodeUseCase by lazy { GenerateQrCodeUseCase(qrCodeGenerator) }
    private val removeLocalFileUseCase by lazy { RemoveLocalFileUseCase() }
    private val generateQrCodeFromTextScenario by lazy {
        GenerateQrCodeFromTextScenario(
            generateQrCodeUseCase
        )
    }

    private val resolveQrCodeUseCase by lazy { ResolveQrCodeUseCase(qrCodeResolver) }

    val messageHandlerFactory =
        MessageHandlerFactory(messageCreateEventTransmitter, generateQrCodeFromTextScenario, resolveQrCodeUseCase)

    companion object {
        lateinit var self: AppCompositionRoot

        fun getInstance(): AppCompositionRoot {
            if (!::self.isInitialized)
                self = AppCompositionRoot()
            return self
        }
    }
}
