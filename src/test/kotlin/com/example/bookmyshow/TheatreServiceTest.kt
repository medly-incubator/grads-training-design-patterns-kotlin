package com.example.bookmyshow


import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class TheatreServiceTest: StringSpec({

    "should return the ticket number on a successful save" {
        val theatreService = TheatreService()
        repeat(15) { theatreService.bookNext(ShowTime.EVENING) }
        val ticketNumber = theatreService.bookNext(ShowTime.EVENING)
        ticketNumber shouldBe 16
    }

    "should throw No Ticket Available exception when all tickets are taken" {
        val theatreService = TheatreService()
        repeat(100) { theatreService.bookNext(ShowTime.EVENING) }
        shouldThrow<NoTicketAvailableException> {
            theatreService.bookNext(ShowTime.EVENING)
        }
    }

    "should save tickets for available shows, throw exception for shows that are full"{
        val theatreService = TheatreService()
        repeat(100) { theatreService.bookNext(ShowTime.NOON) }
        repeat(15) { theatreService.bookNext(ShowTime.EVENING) }

        theatreService.bookNext(ShowTime.EVENING) shouldBe 16
        shouldThrow<NoTicketAvailableException> {
            theatreService.bookNext(ShowTime.NOON)
        }
    }

})
