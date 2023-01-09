package com.SmartCampus.smartcampusmobileapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.SmartCampus.smartcampusmobileapp.Adapter.WaterLevelAdapter
import com.SmartCampus.smartcampusmobileapp.Model.CurrentTrackerAndWaterFlow
import com.SmartCampus.smartcampusmobileapp.Model.WaterLevel
import com.SmartCampus.smartcampusmobileapp.databinding.ActivityWaterLevelBinding
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class WaterLevelActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWaterLevelBinding
    private lateinit var waterLevelList:ArrayList<WaterLevel>
    var adapter: WaterLevelAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWaterLevelBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        waterLevelList = ArrayList<WaterLevel>()
        val adapter = WaterLevelAdapter(this,waterLevelList)
        binding.listView.adapter=adapter


        //get data from parking api

        val queue = Volley.newRequestQueue(this)
        val url = "https://api.thingspeak.com/channels/1953920/feeds.json?results=2"

        val objectRequest= JsonObjectRequest(Request.Method.GET,url,null,{ response->
            //binding.idLoadingPB.visibility = View.GONE
            try {
                // on below line we are getting data from our response
                // and setting it in variables.

                val waterLevelArray = response.getJSONArray("feeds")
                for (i in 0 until waterLevelArray.length()){
                    val waterLevelObject = waterLevelArray.getJSONObject(i)

                    val currentTracker = WaterLevel(waterLevelObject.getString("created_at")
                        ,waterLevelObject.getString("entry_id")
                        ,waterLevelObject.getString("field1")
                        ,waterLevelObject.getString("field2")
                        ,waterLevelObject.getString("field3")
                    )
                    waterLevelList.add(currentTracker)
                    adapter.notifyDataSetChanged()

                }


            } catch (e: Exception) {
                // on below line we are
                // handling our exception.
                e.printStackTrace()
            }},{error->
            Log.e("TAG", "RESPONSE IS $error")
            // in this case we are simply displaying a toast message.
            Toast.makeText(this@WaterLevelActivity, "Fail to get response", Toast.LENGTH_SHORT)
                .show()
        })
        queue.add(objectRequest)
    }
}