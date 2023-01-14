package com.alongo.discordbot.domain.message_handlers

import com.alongo.discordbot.domain.message_handlers.audio.PauseAudioMessageHandler
import com.alongo.discordbot.domain.message_handlers.audio.PlayAudioMessageHandler
import com.alongo.discordbot.domain.message_handlers.audio.ResumeAudioMessageHandler
import com.alongo.discordbot.domain.message_handlers.audio.StopAudioMessageHandler
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
    private val playAudioMessageHandler: PlayAudioMessageHandler,
    private val stopAudioMessageHandler: StopAudioMessageHandler,
    private val pauseAudioMessageHandler: PauseAudioMessageHandler,
    private val resumeAudioMessageHandler: ResumeAudioMessageHandler
) {
    private val map: Map<COMMAND_TYPE, BaseMessageHandler> by lazy {
        mapOf(
            COMMAND_TYPE.KEK to kekMessageHandler,
            COMMAND_TYPE.KEK_CYRILLIC to kekMessageHandler,
            COMMAND_TYPE.QR_ENCODE to qrEncodeMessageHandler,
            COMMAND_TYPE.QR_DECODE to qrDecodeMessageHandler,
            COMMAND_TYPE.HELP to helpMessageHandler,
            COMMAND_TYPE.AUDIO_PLAY to playAudioMessageHandler,
            COMMAND_TYPE.AUDIO_STOP to stopAudioMessageHandler,
            COMMAND_TYPE.AUDIO_PAUSE to pauseAudioMessageHandler,
            COMMAND_TYPE.AUDIO_RESUME to resumeAudioMessageHandler
        )
    }
    fun getHandler(type: COMMAND_TYPE): BaseMessageHandler? = map[type]

}