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
    fun getHandler(type: COMMAND_TYPE): BaseMessageHandler =
        when (type) {
            COMMAND_TYPE.KEK, COMMAND_TYPE.KEK_CYRILLIC -> kekMessageHandler
            COMMAND_TYPE.QR_ENCODE -> qrEncodeMessageHandler
            COMMAND_TYPE.QR_DECODE -> qrDecodeMessageHandler
            COMMAND_TYPE.HELP -> helpMessageHandler
            COMMAND_TYPE.AUDIO_PLAY -> playAudioMessageHandler
            COMMAND_TYPE.AUDIO_STOP -> stopAudioMessageHandler
            COMMAND_TYPE.AUDIO_PAUSE -> pauseAudioMessageHandler
            COMMAND_TYPE.AUDIO_RESUME -> resumeAudioMessageHandler
        }
}