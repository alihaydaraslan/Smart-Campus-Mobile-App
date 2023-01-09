package com.SmartCampus.smartcampusmobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.SmartCampus.smartcampusmobileapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }


    fun onClickParking(view:View){
        val intent = Intent(this@MainActivity,ParkActivity::class.java)
        startActivity(intent)
    }
    fun onClickAirQuality(view:View){
        val intent = Intent(this@MainActivity,AirQualityActivity::class.java)
        startActivity(intent)
    }
    fun onPhotoViewing(view:View){
        val intent = Intent(this@MainActivity,PhotoActivity::class.java)
        startActivity(intent)
    }
    fun onCurrentTracker(view:View){
        val intent = Intent(this@MainActivity,CurrentTrackerActivity::class.java)
        startActivity(intent)
    }
    fun onWaterFlow(view:View){
        val intent = Intent(this@MainActivity,WaterFlowActivity::class.java)
        startActivity(intent)
    }
    fun onWaterLevel(view:View){
        val intent = Intent(this@MainActivity,WaterLevelActivity::class.java)
        startActivity(intent)
    }
}