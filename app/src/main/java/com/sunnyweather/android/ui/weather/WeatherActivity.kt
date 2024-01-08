package com.sunnyweather.android.ui.weather

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.sunnyweather.android.R
import com.sunnyweather.android.logic.model.Weather
import com.sunnyweather.android.logic.model.getSky
import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.android.synthetic.main.forecast.*
import kotlinx.android.synthetic.main.life_index.*
import kotlinx.android.synthetic.main.now.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class WeatherActivity : AppCompatActivity() {

    val viewModel by lazy { ViewModelProviders.of(this).get(WeatherViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= 21) {
            val decorView = window.decorView
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            window.statusBarColor = Color.TRANSPARENT
        }
        setContentView(R.layout.activity_weather)
        if (viewModel.locationName.isEmpty()) {
            viewModel.locationName = intent.getStringExtra("locationName") ?: ""
        }
        viewModel.weatherLiveData.observe(this, Observer { result ->
            val weather = result.getOrNull()
            if (weather != null) {
                showWeatherInfo(weather)
            } else {
                Toast.makeText(this, "無法成功獲得天氣訊息", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
            swipeRefresh.isRefreshing = false
        })
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary)
        refreshWeather()
        swipeRefresh.setOnRefreshListener {
            refreshWeather()
        }
        navBtn.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerStateChanged(newState: Int) {}

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {}

            override fun onDrawerOpened(drawerView: View) {}

            override fun onDrawerClosed(drawerView: View) {
                val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                manager.hideSoftInputFromWindow(drawerView.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        })
    }

    fun refreshWeather() {
        viewModel.refreshWeather(viewModel.locationName)
        swipeRefresh.isRefreshing = true
    }

    private fun showWeatherInfo(weather: Weather) {
        placeName.text = viewModel.locationName
        val realtime = weather.realtime
        val daily = weather.daily
        // 填充now.xml佈局中的數據
        val currentTempText = "${realtime[3].time[0].elementValue[0].eval.toInt()} ℃"
        currentTemp.text = currentTempText
        currentSky.text = realtime[1].time[0].elementValue[0].eval
        val currentCIText = realtime[5].time[0].elementValue[1].eval
        currentCI.text = currentCIText
        nowLayout.setBackgroundResource(R.drawable.bg_clear_day_c)
        // 填充forecast.xml佈局中的數據
        forecastLayout.removeAllViews()

        //val date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) @RequiresApi(Build.VERSION_CODES.O)
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val datego = Date(System.currentTimeMillis())
        val date=formatter.format(datego)
        var returnvalue:Int=0
        //daily[6].time.size 14
        for(i in 0 until daily[6].time.size){
            if(daily[6].time[i].endTime.split(" ")[0]!=date){
                returnvalue=i
                break
            }
        }

        for (i in 0 until 5) {
            var daily_value=daily[6].time[returnvalue].elementValue[1].eval.toInt()
            var daily_value2=daily[6].time[returnvalue+1].elementValue[1].eval.toInt()
            var daily_value3=daily[6].time[returnvalue+2].elementValue[1].eval.toInt()
            var temperature_min = ((daily[8].time[returnvalue].elementValue[0].eval).toInt()+(daily[8].time[returnvalue+1].elementValue[0].eval).toInt()+(daily[8].time[returnvalue+2].elementValue[0].eval).toInt())/3
            var temperature_max = ((daily[12].time[returnvalue].elementValue[0].eval).toInt()+(daily[12].time[returnvalue+1].elementValue[0].eval).toInt()+(daily[12].time[returnvalue+2].elementValue[0].eval).toInt())/3

            val view = LayoutInflater.from(this).inflate(R.layout.forecast_item, forecastLayout, false)
            val dateInfo = view.findViewById(R.id.dateInfo) as TextView
            val skyIcon = view.findViewById(R.id.skyIcon) as ImageView
            val temperatureInfo = view.findViewById(R.id.temperatureInfo) as TextView

            dateInfo.text = daily[6].time[returnvalue].endTime.split(" ")[0]
            skyIcon.setImageResource((getSky((daily_value+daily_value2+daily_value3)/3)).icon)

            returnvalue += 2

            val tempText = "${temperature_min} ~ ${temperature_max} ℃"
            temperatureInfo.text = tempText
            forecastLayout.addView(view)
        }
        // 填充life_index.xml佈局中的數據
        val lifeIndex = realtime[6].time[0].elementValue[0].eval
        life_index1.text = lifeIndex.split("。")[0]
        life_index2.text = lifeIndex.split("。")[1]
        life_index3.text = lifeIndex.split("。")[2]
        life_index4.text = lifeIndex.split("。")[3]
        life_index5.text = lifeIndex.split("。")[4]
        life_index6.text = lifeIndex.split("。")[5]
        weatherLayout.visibility = View.VISIBLE
    }

}
