package com.example.rickandmortyapp_lab2

import retrofit2.Response
import retrofit2.http.GET

interface RickAndMortyApi {
    @GET("character")
    suspend fun getCharacters(): Response<CharacterResponse>
}