package com.alongo.discordbot.domain.message_handlers

import com.alongo.discordbot.feature.command.Command
import com.alongo.discordbot.utils.CommandStorageFactory
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.BeforeTest

internal class CommandTypeResolverTest {
    private val commandTypeResolver = CommandTypeResolver

    @BeforeTest
    fun setup() {
        commandTypeResolver.setup(
            CommandStorageFactory.create(),
            commandMarker = validCommandMarker
        )
    }

    @Test
    fun testKekCommandsQuery() {
        val kekQueries = listOf(
            "Я сходил в турецкий супермаркет и купил себе кек к чаю",
            "Рецепт кекса очень прост: возьмите...",
            "I saw a pukeko on my recent trip to the New Zealand!",
            "A Key Encryption Key or KEK is simply a key that is solely used to encrypt keys."
        )
        for (query in kekQueries) {
            assertEquals(commandTypeResolver.resolve(query), Command.KEK)
        }
    }

    @Test
    fun testPlayAudioCommandsQuery() {
        val validQueries =
            listOf(
                "${validCommandMarker}play music",
                "${validCommandMarker}play https://example.com",
            )
        val failingQueries = listOf(
            "Alexa, play despacito",
            "Press !play to win",
            "play music"
        )
        for (query in validQueries) {
            assertEquals(commandTypeResolver.resolve(query), Command.AUDIO.PLAY)
        }

        for (query in failingQueries) {
            assertEquals(commandTypeResolver.resolve(query), null)
        }
    }

    companion object {
        const val validCommandMarker = "!"
    }
}
