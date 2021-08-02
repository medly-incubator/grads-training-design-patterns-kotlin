package com.example.bookmyshow


import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class TheatreServiceTest: StringSpec({

    "should return the ticket number on a successful save" {
        val theatreRepository = mockk<TheatreRepository>()
        val theatreService = TheatreService(theatreRepository)
        every {theatreRepository.save()} returns 5
        val ticketNumber = theatreService.bookNext()
        ticketNumber shouldBe 5
    }

    "should throw No Ticket Available exception when all tickets are taken" {
        val theatreRepository = mockk<TheatreRepository>()
        val theatreService = TheatreService(theatreRepository)
        every {theatreRepository.save()} throws NoTicketAvailableException()
        shouldThrow<NoTicketAvailableException> {
            theatreService.bookNext()
        }
    }

})
