package com.alongo.discordbot

// Application entry point object
object Main {
    @Suppress("MemberNameEqualsClassName") // Required to run the app
    @JvmStatic
    fun main(args: Array<String>) {
        Application().start()
    }
}
