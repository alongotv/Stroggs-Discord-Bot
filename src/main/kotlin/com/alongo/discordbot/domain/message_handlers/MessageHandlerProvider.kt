package com.alongo.discordbot.domain.message_handlers

import com.alongo.discordbot.domain.message_handlers.audio.PauseAudioMessageHandler
import com.alongo.discordbot.domain.message_handlers.audio.PlayAudioMessageHandler
import com.alongo.discordbot.domain.message_handlers.audio.ResumeAudioMessageHandler
import com.alongo.discordbot.domain.message_handlers.audio.StopAudioMessageHandler
import com.alongo.discordbot.domain.message_handlers.misc.HelpMessageHandler
import com.alongo.discordbot.domain.message_handlers.misc.KekMessageHandler
import com.alongo.discordbot.domain.message_handlers.qr.QrDecodeMessageHandler
import com.alongo.discordbot.domain.message_handlers.qr.QrEncodeMessageHandler
import com.alongo.discordbot.feature.command.Command
import javax.inject.Inject

@Suppress("LongParameterList")
class MessageHandlerProvider @Inject constructor(
    private val kekMessageHandler: KekMessageHandler,
    private val qrEncodeMessageHandler: QrEncodeMessageHandler,
    private val qrDecodeMessageHandler: QrDecodeMessageHandler,
    private val helpMessageHandler: HelpMessageHandler,
    private val playAudioMessageHandler: PlayAudioMessageHandler,
    private val stopAudioMessageHandler: StopAudioMessageHandler,
    private val pauseAudioMessageHandler: PauseAudioMessageHandler,
    private val resumeAudioMessageHandler: ResumeAudioMessageHandler
) {
    private val map: Map<Command, BaseMessageHandler> by lazy {
        mapOf(
            Command.KEK to kekMessageHandler,
            Command.QR.ENCODE to qrEncodeMessageHandler,
            Command.QR.DECODE to qrDecodeMessageHandler,
            Command.HELP to helpMessageHandler,
            Command.AUDIO.PLAY to playAudioMessageHandler,
            Command.AUDIO.STOP to stopAudioMessageHandler,
            Command.AUDIO.PAUSE to pauseAudioMessageHandler,
            Command.AUDIO.RESUME to resumeAudioMessageHandler,
        )
    }

    fun getHandler(type: Command): BaseMessageHandler? = map[type]
}
