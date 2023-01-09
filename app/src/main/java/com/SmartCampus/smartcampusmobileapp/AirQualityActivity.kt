package com.SmartCampus.smartcampusmobileapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.SmartCampus.smartcampusmobileapp.Model.AirQuality
import com.SmartCampus.smartcampusmobileapp.databinding.ActivityAirQualityBinding
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class AirQualityActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAirQualityBinding
    private lateinit var airQualityList:ArrayList<AirQuality>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAirQualityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        airQualityList=ArrayList<AirQuality>()

        //get data from api
        val queue = Volley.newRequestQueue(this)
        val url = "https://api.thingspeak.com/channels/1981609/feeds.json?api_key=VMB2MTUIUPESHNCD&results=1"

        val objectRequest= JsonObjectRequest(Request.Method.GET,url,null,{ response->
            //binding.idLoadingPB.visibility = View.GONE
            try {
                // on below line we are getting data from our response
                // and setting it in variables.
                val airQualityArray = response.getJSONArray("feeds")
                for (i in 0 until airQualityArray.length()){
                    val airQualityObject = airQualityArray.getJSONObject(i)

                    val airQuality = AirQuality(airQualityObject.getString("field1")
                        ,airQualityObject.getString("field2")
                        ,airQualityObject.getString("field3")
                        ,airQualityObject.getString("field4")
                        ,airQualityObject.getString("field5")
                        ,airQualityObject.getString("created_at"))

                    airQualityList.add(airQuality)

                }


                var str="Air Quality"
                for (i in 0 until airQualityList.size){
                    str=str+"\n"+"CO2: "+airQualityList[i].co2+
                            "\n"+"CO: "+airQualityList[i].co+
                            "\n"+"NH4: "+airQualityList[i].nh4+
                            "\n"+"Alcohol: "+airQualityList[i].alcohol+
                            "\n"+"PPM : "+airQualityList[i].ppm+
                            "\n"+"Created At : "+airQualityList[i].createdAt
                }
                binding.textView.text = str

            } catch (e: Exception) {
                // on below line we are
                // handling our exception.
                e.printStackTrace()
            }},{error->
            Log.e("TAG", "RESPONSE IS $error")
            // in this case we are simply displaying a toast message.
            Toast.makeText(this@AirQualityActivity, "Fail to get response", Toast.LENGTH_SHORT)
                .show()
        })
        queue.add(objectRequest)

    }
}