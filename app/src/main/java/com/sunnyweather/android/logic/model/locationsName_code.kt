package com.sunnyweather.android.logic.model

private val locationsName_code = mapOf(

    "宜蘭縣" to "F-D0047-003",
    "桃園市" to "F-D0047-007",
    "新竹縣" to "F-D0047-011",
    "苗栗縣" to "F-D0047-015",
    "彰化縣" to "F-D0047-019",
    "南投縣" to "F-D0047-023",
    "雲林縣" to "F-D0047-027",
    "嘉義縣" to "F-D0047-031",
    "屏東縣" to "F-D0047-035",
    "臺東縣" to "F-D0047-039",
    "花蓮縣" to "F-D0047-043",
    "澎湖縣" to "F-D0047-047",
    "基隆市" to "F-D0047-051",
    "新竹市" to "F-D0047-055",
    "嘉義市" to "F-D0047-059",
    "臺北市" to "F-D0047-063",
    "高雄市" to "F-D0047-067",
    "新北市" to "F-D0047-071",
    "臺中市" to "F-D0047-075",
    "臺南市" to "F-D0047-079",
    "連江縣" to "F-D0047-083",
    "金門縣" to "F-D0047-087",
    "" to ""
)

fun getlocationsName_code(code: String): String {
    return locationsName_code[code] ?: locationsName_code[""]!!
}