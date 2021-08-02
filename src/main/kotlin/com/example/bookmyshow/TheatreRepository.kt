package com.example.bookmyshow

class TheatreRepository {
    private val ticketsSoldByShow = mutableMapOf<ShowTime, Int>()

    fun save(showTime: ShowTime): Int {
        var lastSavedTicket = ticketsSoldByShow.getOrDefault(showTime, 0)
        lastSavedTicket += 1
        ticketsSoldByShow.put(showTime, lastSavedTicket)
        return lastSavedTicket
    }

    fun lastSaved(showTime: ShowTime): Int {
        return ticketsSoldByShow.getOrDefault(showTime, 0)
    }

}
