package com.sunnyweather.android.logic.model

private val locationsName_code2 = mapOf(

    "宜蘭縣" to "F-D0047-001",
    "桃園市" to "F-D0047-005",
    "新竹縣" to "F-D0047-009",
    "苗栗縣" to "F-D0047-013",
    "彰化縣" to "F-D0047-017",
    "南投縣" to "F-D0047-021",
    "雲林縣" to "F-D0047-025",
    "嘉義縣" to "F-D0047-029",
    "屏東縣" to "F-D0047-033",
    "臺東縣" to "F-D0047-037",
    "花蓮縣" to "F-D0047-041",
    "澎湖縣" to "F-D0047-045",
    "基隆市" to "F-D0047-049",
    "新竹市" to "F-D0047-053",
    "嘉義市" to "F-D0047-057",
    "臺北市" to "F-D0047-061",
    "高雄市" to "F-D0047-065",
    "新北市" to "F-D0047-069",
    "臺中市" to "F-D0047-073",
    "臺南市" to "F-D0047-077",
    "連江縣" to "F-D0047-081",
    "金門縣" to "F-D0047-085",
    "" to ""
)

fun getlocationsName_code2(code2: String): String {
    return locationsName_code2[code2] ?: locationsName_code2[""]!!
}