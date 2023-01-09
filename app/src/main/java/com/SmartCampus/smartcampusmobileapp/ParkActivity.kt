package com.SmartCampus.smartcampusmobileapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.SmartCampus.smartcampusmobileapp.Model.Parking
import com.SmartCampus.smartcampusmobileapp.databinding.ActivityParkBinding
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class ParkActivity : AppCompatActivity() {

    private lateinit var binding: ActivityParkBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityParkBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //get data from parking api

        val queue = Volley.newRequestQueue(this)
        val url = "https://api.thingspeak.com/channels/1910690/fields/1/last.json"

        val objectRequest= JsonObjectRequest(Request.Method.GET,url,null,{ response->
            //binding.idLoadingPB.visibility = View.GONE
            try {
                // on below line we are getting data from our response
                // and setting it in variables.
                val parkingData = Parking(response.getString("field1"),response.getString("created_at"))

                val parkDataArray = parkingData.parkingData.chunked(1)

                var str="Parking Area Status"
                for (i in parkDataArray.indices){
                    if(parkDataArray[i]=="1")
                        str=str+"\n"+"Park Area "+(i+1)+": busy"
                    else
                        str=str+"\n"+"Park Area "+(i+1)+": empty"
                }
                str=str+"\n"+"Created At: "+parkingData.createdAt

                binding.textView.text=str


            } catch (e: Exception) {
                // on below line we are
                // handling our exception.
                e.printStackTrace()
            }},{error->
            Log.e("TAG", "RESPONSE IS $error")
            // in this case we are simply displaying a toast message.
            Toast.makeText(this@ParkActivity, "Fail to get response", Toast.LENGTH_SHORT)
                .show()
        })
        queue.add(objectRequest)
    }
}