package com.sunnyweather.android.logic.model

import com.google.gson.annotations.SerializedName

class RealtimeResponse(val success: String, val records: Records) {

    class Records(val locations:List<Locations>)

    class Locations(val location: List<Location>)

    class Location(val locationName: String, val weatherElement:List<WeatherElement>)

    class WeatherElement(val elementName:String, val description:String, val time:List<Time>)

    class Time(val startTime:String, val endTime:String, val elementValue:List<ElementValue>)

    class ElementValue(@SerializedName("value") val eval:String, val measures:String)

}