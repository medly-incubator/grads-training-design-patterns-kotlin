package com.example.bookmyshow

class TheatreService(private val theatreRepository: TheatreRepository) {

    companion object {
        val AVAILABLE_SEATS = 100
    }

    fun bookNext(): Int {
        val lastSavedTicket = theatreRepository.lastSaved()
        if(lastSavedTicket == AVAILABLE_SEATS)
            throw NoTicketAvailableException()
        return theatreRepository.save()
    }
}

