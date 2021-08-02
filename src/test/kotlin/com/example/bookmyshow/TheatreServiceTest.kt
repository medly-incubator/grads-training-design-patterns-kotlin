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
        every {theatreRepository.lastSaved(ShowTime.EVENING) } returns 5
        every {theatreRepository.save(ShowTime.EVENING) } returns 6
        val ticketNumber = theatreService.bookNext(ShowTime.EVENING)
        ticketNumber shouldBe 6
        verify { theatreRepository.save(ShowTime.EVENING) }
    }

    "should throw No Ticket Available exception when all tickets are taken" {
        val theatreRepository = mockk<TheatreRepository>()
        val theatreService = TheatreService(theatreRepository)
        every {theatreRepository.lastSaved(ShowTime.EVENING) } returns 100
        shouldThrow<NoTicketAvailableException> {
            theatreService.bookNext(ShowTime.EVENING)
        }
    }

    "should save ticket for multiple shows" {
        val theatreRepository = mockk<TheatreRepository>()
        val theatreService = TheatreService(theatreRepository)
        every {theatreRepository.lastSaved(ShowTime.NIGHT)} returns 20
        every { theatreRepository.lastSaved(ShowTime.AFTERNOON)} returns 100
        every {theatreRepository.save(ShowTime.NIGHT)} returns 21
        theatreService.bookNext(ShowTime.NIGHT)
        verify { theatreRepository.save(ShowTime.NIGHT) }
        shouldThrow<NoTicketAvailableException> {
            theatreService.bookNext(ShowTime.AFTERNOON)
        }
    }


})
