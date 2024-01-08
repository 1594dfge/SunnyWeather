package com.sunnyweather.android.ui.weather

import android.util.Log
import androidx.lifecycle.*
import com.sunnyweather.android.logic.Repository
import com.sunnyweather.android.logic.model.Location
import com.sunnyweather.android.logic.model.getlocationsName
import com.sunnyweather.android.logic.model.getlocationsName_code
import com.sunnyweather.android.logic.model.getlocationsName_code2

class WeatherViewModel : ViewModel() {

    private val locationLiveData = MutableLiveData<Location>()

    var locationName = ""

    val weatherLiveData = Transformations.switchMap(locationLiveData) { location ->
        Repository.refreshWeather(getlocationsName(location.locationName),location.locationName)
    }

    fun refreshWeather(query: String) {
        locationLiveData.value = Location(query)
    }

}