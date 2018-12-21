package com.jlccaires.norrisfacts.data

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NorrisService {
    @GET("/jokes/random")
    fun getJoke(@Query("category") category: String): Observable<JokeDto>

    @GET("/jokes/categories")
    fun listCategories(): Observable<List<String>>

    @GET("/jokes/search")
    fun search(@Query("query") query: String): Observable<JokeDto>
}