package dev.charan.versiontracker.retrofit

import dev.charan.versiontracker.model.AutoUpdateDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AutoUpdateAPIService {
    @GET("/getData")
    suspend fun fetchLatestAppVersion(
        @Query("apiKey") apiKey: String ,
        @Query("appName") appName : String
    ) : Response<AutoUpdateDTO>
}