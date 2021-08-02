package com.example.bookmyshow

class TheatreRepository {
    companion object {
        val AVAILABLE_SEATS = 100
    }

    var lastSavedTicket: Int = 0
    fun save(): Int {
        if(lastSavedTicket == AVAILABLE_SEATS)
            throw NoTicketAvailableException()
        lastSavedTicket++;
        return lastSavedTicket;
    }

}
