package com.SmartCampus.smartcampusmobileapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.SmartCampus.smartcampusmobileapp.Adapter.CurrentTrackerWaterFlowAdapter
import com.SmartCampus.smartcampusmobileapp.Model.CurrentTrackerAndWaterFlow
import com.SmartCampus.smartcampusmobileapp.databinding.ActivityWaterFlowBinding
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class WaterFlowActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWaterFlowBinding
    private lateinit var waterFlowList:ArrayList<CurrentTrackerAndWaterFlow>
    var adapter: CurrentTrackerWaterFlowAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWaterFlowBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        waterFlowList = ArrayList<CurrentTrackerAndWaterFlow>()
        val adapter = CurrentTrackerWaterFlowAdapter(this,waterFlowList)
        binding.listView.adapter=adapter


        //get data from parking api

        val queue = Volley.newRequestQueue(this)
        val url = "https://api.thingspeak.com/channels/1979065/feeds.json?results=2"

        val objectRequest= JsonObjectRequest(Request.Method.GET,url,null,{ response->
            //binding.idLoadingPB.visibility = View.GONE
            try {
                // on below line we are getting data from our response
                // and setting it in variables.

                val currentTrackerArray = response.getJSONArray("feeds")
                for (i in 0 until currentTrackerArray.length()){
                    val currentTrackerObject = currentTrackerArray.getJSONObject(i)

                    val currentTracker = CurrentTrackerAndWaterFlow(currentTrackerObject.getString("entry_id")
                        ,currentTrackerObject.getString("field1")
                        ,currentTrackerObject.getString("created_at")
                    )
                    waterFlowList.add(currentTracker)
                    adapter.notifyDataSetChanged()

                }


            } catch (e: Exception) {
                // on below line we are
                // handling our exception.
                e.printStackTrace()
            }},{error->
            Log.e("TAG", "RESPONSE IS $error")
            // in this case we are simply displaying a toast message.
            Toast.makeText(this@WaterFlowActivity, "Fail to get response", Toast.LENGTH_SHORT)
                .show()
        })
        queue.add(objectRequest)





    }
}