package com.alongo.discordbot.data.datasource.networking

import com.alongo.discordbot.domain.entity.dto.voice.GenerateTextToVoiceResponseDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import javax.inject.Inject

class VoiceApiImpl @Inject constructor(private val httpClient: HttpClient) : VoiceApi {

    override suspend fun generateSpeech(content: String): String {
        val result = httpClient.post("https://ttsmp3.com/makemp3_new.php") {
//            contentType(ContentType.Application.FormUrlEncoded)
            headers {
                append("Host", "ttsmp3.com")
                append("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:104.0) Gecko/20100101 Firefox/104.0")
                append("Accept", "*/*")
                append("Content-Type", "application/x-www-form-urlencoded")
                append("Accept-Language", "ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3")
                append("Accept-Encoding", "gzip, deflate, br")
                append("Origin", "https://ttsmp3.com")
                append("Connection", "keep-alive")
                append("Referer", "https://ttsmp3.com")
                append("Cookie", "cookieconsent_status=deny; __stripe_mid=30bc60ea-4174-467c-9702-93f6b787212308770c; __stripe_sid=15e73c0c-d67e-4a75-8f8d-4c4c5fc0029c533beb")
                append("Sec-Fetch-Dest", "empty")
                append("Sec-Fetch-Mode", "cors")
                append("Sec-Fetch-Site", "same-origin")
            }
            setBody("msg=$content&lang=Maxim&source=ttsmp3")
        }.body<GenerateTextToVoiceResponseDto>()

        return result.URL
    }
}

//curl "https://ttsmp3.com/makemp3_new.php" -X POST -H "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:104.0) Gecko/20100101 Firefox/104.0" -H "Accept: */*" -H "Accept-Language: ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3" -H "Accept-Encoding: gzip, deflate, br" -H "Referer: https://ttsmp3.com/text-to-speech/Russian/" -H "Origin: https://ttsmp3.com" -H "Connection: keep-alive" -H "Cookie: cookieconsent_status=deny; __stripe_mid=30bc60ea-4174-467c-9702-93f6b787212308770c; __stripe_sid=15e73c0c-d67e-4a75-8f8d-4c4c5fc0029c533beb" -H "Sec-Fetch-Dest: empty" -H "Sec-Fetch-Mode: no-cors" -H "Sec-Fetch-Site: same-origin" -H "Content-type: application/x-www-form-urlencoded" -H "Pragma: no-cache" -H "Cache-Control: no-cache" --data-raw "msg="%"D0"%"B1"%"D0"%"BE"%"D1"%"82"%"20"%"D0"%"BC"%"D0"%"B0"%"D0"%"BA"%"D1"%"81"%"D0"%"B8"%"D0"%"BC"%"20"%"D1"%"81"%"D0"%"B8"%"D0"%"BD"%"D1"%"82"%"D0"%"B5"%"D0"%"B7"%"D0"%"B0"%"D1"%"82"%"D0"%"BE"%"D1"%"80"%"20"%"D1"%"80"%"D0"%"B5"%"D1"%"87"%"D0"%"B8&lang=Maxim&source=ttsmp3"