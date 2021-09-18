package com.example.pixelmonsterapp3.data.network

import com.example.pixelmonsterapp3.data.network.response.MonsterDetailsResponse
import com.example.pixelmonsterapp3.data.network.response.MonsterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface MonsterApi {

    @GET("api/basic/monsterdetails/{id}")
    suspend fun getMonsterDetails(@Path("id") id: Int): Response<MonsterDetailsResponse>

    /**
     * max skip = 2250
     * */
    @GET("odata/basic/monsterdetails")
    suspend fun getMonsters(
        @Query("\$top") top: Int,
        @Query("\$skip") skip: Int,
    ): Response<MonsterResponse>
}
