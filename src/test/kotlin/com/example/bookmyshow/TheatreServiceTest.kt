package com.example.bookmyshow


import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class TheatreServiceTest: StringSpec({

    "should return the ticket number on a successful save" {
        val theatreRepository = mockk<TheatreRepository>()
        val theatreService = TheatreService(theatreRepository)
        every {theatreRepository.lastSaved()} returns 5
        every {theatreRepository.save()} returns 6
        val ticketNumber = theatreService.bookNext()
        ticketNumber shouldBe 6
        verify { theatreRepository.save() }
    }

    "should throw No Ticket Available exception when all tickets are taken" {
        val theatreRepository = mockk<TheatreRepository>()
        val theatreService = TheatreService(theatreRepository)
        every {theatreRepository.lastSaved()} returns 100
        shouldThrow<NoTicketAvailableException> {
            theatreService.bookNext()
        }
    }


})
