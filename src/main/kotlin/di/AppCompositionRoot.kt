package di

import data.MessageCreateEventTransmitter
import domain.MessageHandlerFactory

class AppCompositionRoot private constructor() {

    val messageCreateEventTransmitter = MessageCreateEventTransmitter()
    val messageHandlerFactory = MessageHandlerFactory(messageCreateEventTransmitter)

    companion object {
        lateinit var self: AppCompositionRoot

        fun getInstance(): AppCompositionRoot {
            if (!::self.isInitialized)
                self = AppCompositionRoot()
            return self
        }
    }
}
