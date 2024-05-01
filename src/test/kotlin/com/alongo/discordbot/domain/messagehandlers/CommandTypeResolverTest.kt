package com.alongo.discordbot.domain.messagehandlers

import com.alongo.discordbot.feature.command.Command
import com.alongo.discordbot.utils.CommandStorageFactory
import org.junit.jupiter.api.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

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
        val validQueries = listOf(
            "Я сходил в турецкий супермаркет и купил себе кек к чаю",
            "Рецепт кекса очень прост: возьмите...",
            "I saw a pukeko on my recent trip to the New Zealand!",
            "A Key Encryption Key or KEK is simply a key that is solely used to encrypt keys."
        )

        executeTest(validQueries, emptyList(), commandUnderTest = Command.KEK)
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
            "Press ${validCommandMarker}play to win",
            "play music",
            "${validCommandMarker}playarandomtrack"
        )

        executeTest(validQueries, failingQueries, Command.AUDIO.PLAY)
    }

    @Test
    fun testPauseAudioCommandsQuery() {
        val validQueries =
            listOf(
                "${validCommandMarker}pause",
                "${validCommandMarker}pause https://example.com",
            )

        val failingQueries = listOf("pause", "${validCommandMarker}pause2")
        executeTest(validQueries, failingQueries, Command.AUDIO.PAUSE)
    }

    @Test
    fun testResumeAudioCommandsQuery() {
        val validQueries =
            listOf(
                "${validCommandMarker}resume",
                "${validCommandMarker}resume https://example.com",
            )

        val failingQueries = listOf("resume", "${validCommandMarker}resume2")
        executeTest(validQueries, failingQueries, Command.AUDIO.RESUME)
    }

    @Test
    fun testStopAudioCommandsQuery() {
        val validQueries =
            listOf(
                "${validCommandMarker}stop",
                "${validCommandMarker}stop https://example.com",
            )

        val failingQueries = listOf("stop", "${validCommandMarker}stop2")
        executeTest(validQueries, failingQueries, Command.AUDIO.STOP)
    }

    private fun executeTest(
        validQueries: List<String>,
        failingQueries: List<String>,
        commandUnderTest: Command
    ) {
        for (query in validQueries) {
            assertEquals(commandTypeResolver.resolve(query), commandUnderTest)
        }
        for (query in failingQueries) {
            assertNotEquals(commandTypeResolver.resolve(query), commandUnderTest)
        }
    }

    companion object {
        const val validCommandMarker = "!"
    }
}
