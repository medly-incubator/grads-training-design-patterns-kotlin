package com.example.bookmyshow

class TheatreService(private val theatreRepository: TheatreRepository) {

    fun bookNext(): Int {
        return theatreRepository.save()
    }
}

