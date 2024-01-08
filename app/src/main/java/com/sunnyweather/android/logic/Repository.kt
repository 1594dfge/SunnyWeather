package com.sunnyweather.android.logic

import android.util.Log
import androidx.lifecycle.liveData
import com.sunnyweather.android.logic.dao.PlaceDao
import com.sunnyweather.android.logic.model.Location
import com.sunnyweather.android.logic.model.Weather
import com.sunnyweather.android.logic.model.getlocationsName_code
import com.sunnyweather.android.logic.model.getlocationsName_code2
import com.sunnyweather.android.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.CoroutineContext

object Repository {

    fun searchPlaces(query: String) = fire(Dispatchers.IO) {
        val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
        if (placeResponse.success == "true") {
            val places = placeResponse.records.locations[0].location
            Result.success(places)
        } else {
            Result.failure(RuntimeException("response status is ${placeResponse.success}"))
        }
    }

    fun refreshWeather(query: String, query2: String) = fire(Dispatchers.IO) {
        coroutineScope {
            val deferredRealtime = async {
                SunnyWeatherNetwork.getRealtimeWeather(getlocationsName_code2(query), query2)
            }
            val deferredDaily = async {
                SunnyWeatherNetwork.getDailyWeather(getlocationsName_code(query), query2)
            }
            val realtimeResponse = deferredRealtime.await()
            val dailyResponse = deferredDaily.await()
            if (realtimeResponse.success == "true" && dailyResponse.success == "true") {
                val weather = Weather(realtimeResponse.records.locations[0].location[0].weatherElement, dailyResponse.records.locations[0].location[0].weatherElement)
                Result.success(weather)
            } else {
                Result.failure(
                    RuntimeException(
                        "realtime response status is ${realtimeResponse.success}" +
                                "daily response status is ${dailyResponse.success}"
                    )
                )
            }
        }
    }

    fun savePlace(place: Location) = PlaceDao.savePlace(place)

    fun getSavedPlace() = PlaceDao.getSavedPlace()

    fun isPlaceSaved() = PlaceDao.isPlaceSaved()

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }

}