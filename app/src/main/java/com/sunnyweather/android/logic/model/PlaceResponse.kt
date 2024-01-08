package com.sunnyweather.android.logic.model

import com.google.gson.annotations.SerializedName

class PlaceResponse(val success: String, val records: Records)

class Records(val locations:List<Locations>)

class Locations(val location: List<Location>)

class Location(val locationName: String)