package com.drunken.valostats.network

import com.drunken.valostats.models.*
import com.drunken.valostats.models.Map
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://valorant-api.com/v1/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @GET("agents")
    suspend fun getAllAgents(@Query("isPlayableCharacter") filter: Boolean = true): Response<List<Agent>>

    @GET("agents/{agentUuid}")
    suspend fun getAgent(@Path("agentUuid") agentId: String): Response<Agent>

    @GET("maps")
    suspend fun getAllMaps(): Response<List<Map>>

    @GET("maps/{mapUuid}")
    suspend fun getMap(@Path("mapUuid") mapId: String): Response<Map>

    @GET("bundles")
    suspend fun getAllBundles(): Response<List<Bundle>>

    @GET("bundles/{bundleUuid}")
    suspend fun getBundle(@Path("bundleUuid") bundleId: String): Response<Bundle>

    @GET("sprays")
    suspend fun getAllSprays(): Response<List<Spray>>

    @GET("sprays/{sprayUuid}")
    suspend fun getSpray(@Path("sprayUuid") sprayId: String): Response<Spray>
}

object ValorantApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}