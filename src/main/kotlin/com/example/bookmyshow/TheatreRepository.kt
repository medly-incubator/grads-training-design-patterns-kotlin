package com.example.bookmyshow

class TheatreRepository {
    private var lastSavedTicket: Int = 0
    fun save(): Int {
        lastSavedTicket+=1
        return lastSavedTicket
    }

    fun lastSaved(): Int {
        return lastSavedTicket
    }

}
