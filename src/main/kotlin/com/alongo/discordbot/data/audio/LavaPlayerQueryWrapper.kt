package com.alongo.discordbot.data.audio

import java.net.MalformedURLException
import java.net.URL
import javax.inject.Inject

class LavaPlayerQueryWrapper @Inject constructor() {
    fun wrap(query: String): String {
        // Check if URL is valid; otherwise, try youtube search
        return try {
            URL(query)
            query
        } catch (e: MalformedURLException) {
            "ytsearch: $query"
        }
    }
}
