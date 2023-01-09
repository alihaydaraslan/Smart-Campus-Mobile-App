package com.SmartCampus.smartcampusmobileapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.SmartCampus.smartcampusmobileapp.databinding.ActivityPhotoBinding
import com.cloudinary.*
import com.cloudinary.transformation.resize.Resize
import com.google.android.gms.cast.tv.media.MediaManager
import com.squareup.picasso.Picasso


class PhotoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPhotoBinding
    private var cloudinary = Cloudinary("cloudinary://895649841822453:RCB7Lbv08xZi3A1XVwiDI8_MYdI@ddgj3spiz")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

       val myImage = "https://res.cloudinary.com/ddgj3spiz/image/upload/v1666906635/Example/images4.png"


        Picasso.get().load(myImage).into(binding.imageView)


    }
}