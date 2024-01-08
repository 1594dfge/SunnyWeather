package com.sunnyweather.android.logic.network

import com.sunnyweather.android.SunnyWeatherApplication
import com.sunnyweather.android.logic.model.DailyResponse
import com.sunnyweather.android.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {

    @GET("v1/rest/datastore/{query}?Authorization=${SunnyWeatherApplication.TOKEN}") //未來兩天
    fun getRealtimeWeather(@Path("query") query: String, @Query("locationName") query2: String): Call<RealtimeResponse>

    @GET("v1/rest/datastore/{query}?Authorization=${SunnyWeatherApplication.TOKEN}") //未來七天
    fun getDailyWeather(@Path("query") query: String, @Query("locationName") query2: String): Call<DailyResponse>

}