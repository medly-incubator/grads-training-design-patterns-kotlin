package com.example.bookmyshow

import com.example.bookmyshow.ShowTime.EVENING
import com.example.bookmyshow.ShowTime.NOON
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class TheatreServiceIntegrationTest : StringSpec({
    "should save tickets for available shows, throw exception for shows that are full"{
        val theatreService = TheatreService(TheatreRepository())
        repeat(100) { theatreService.bookNext(NOON) }
        repeat(15) { theatreService.bookNext(EVENING) }

        theatreService.bookNext(EVENING) shouldBe 16
        shouldThrow<NoTicketAvailableException> {
            theatreService.bookNext(NOON)
        }
    }
})