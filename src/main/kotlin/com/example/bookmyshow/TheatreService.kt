package com.example.bookmyshow

class TheatreService {
    private val ticketsSoldByShow = mutableMapOf<ShowTime, Int>()

    companion object {
        val AVAILABLE_SEATS = 100
    }

    fun bookNext(showTime: ShowTime): Int {
        val lastSavedTicket = ticketsSoldByShow.getOrDefault(showTime, 0)
        if (lastSavedTicket == AVAILABLE_SEATS)
            throw NoTicketAvailableException()
        var lastSavedTicket1 = ticketsSoldByShow.getOrDefault(showTime, 0)
        lastSavedTicket1 += 1
        ticketsSoldByShow.put(showTime, lastSavedTicket1)
        return lastSavedTicket1
    }
}

