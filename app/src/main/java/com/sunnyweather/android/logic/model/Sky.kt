package com.sunnyweather.android.logic.model

import com.sunnyweather.android.R

class Sky (val icon: Int)

private val sky = mapOf(
    1 to Sky(R.drawable._1),
    2 to Sky(R.drawable._2),
    3 to Sky(R.drawable._3),
    4 to Sky(R.drawable._4),
    5 to Sky(R.drawable._5),
    6 to Sky(R.drawable._6),
    7 to Sky(R.drawable._7),
    8 to Sky(R.drawable._8),
    9 to Sky(R.drawable._9),
    10 to Sky(R.drawable._10),
    11 to Sky(R.drawable._11),
    12 to Sky(R.drawable._12),
    13 to Sky(R.drawable._13),
    14 to Sky(R.drawable._14),
    15 to Sky(R.drawable._15),
    16 to Sky(R.drawable._16),
    17 to Sky(R.drawable._17),
    18 to Sky(R.drawable._18),
    19 to Sky(R.drawable._19),
    20 to Sky(R.drawable._20),
    21 to Sky(R.drawable._21),
    22 to Sky(R.drawable._22),
    23 to Sky(R.drawable._23),
    24 to Sky(R.drawable._24),
    25 to Sky(R.drawable._25),
    26 to Sky(R.drawable._26),
    27 to Sky(R.drawable._27),
    28 to Sky(R.drawable._28),
    29 to Sky(R.drawable._29),
    30 to Sky(R.drawable._30),
    31 to Sky(R.drawable._31),
    32 to Sky(R.drawable._32),
    33 to Sky(R.drawable._33),
    34 to Sky(R.drawable._34),
    35 to Sky(R.drawable._35),
    36 to Sky(R.drawable._36),
    37 to Sky(R.drawable._37),
    38 to Sky(R.drawable._38),
    39 to Sky(R.drawable._39),
    41 to Sky(R.drawable._41),
    42 to Sky(R.drawable._42)

)

fun getSky(skycode: Int): Sky {
    return sky[skycode] ?: sky[1]!!
}