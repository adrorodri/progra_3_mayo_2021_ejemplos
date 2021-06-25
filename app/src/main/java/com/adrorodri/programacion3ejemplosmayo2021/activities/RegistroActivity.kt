package com.adrorodri.programacion3ejemplosmayo2021.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.FileProvider
import com.adrorodri.programacion3ejemplosmayo2021.R
import com.bumptech.glide.Glide
import java.io.File

class RegistroActivity : AppCompatActivity() {

    lateinit var imageViewProfilePicture: ImageView
    lateinit var buttonCamera: Button
    lateinit var buttonGalery: Button

    val requestCodeGaleria = 113
    val requestCodeCamera = 114

    var fileUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        imageViewProfilePicture = findViewById(R.id.imageViewProfilePicture)
        buttonCamera = findViewById(R.id.buttonCamera)
        buttonGalery = findViewById(R.id.buttonGaleria)

        buttonCamera.setOnClickListener {
            val intent = Intent()
            val file = File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "ejemploFoto_${System.currentTimeMillis()}.jpg")
            fileUri = FileProvider.getUriForFile(this, "com.adrorodri.programacio3ejemplosmayo2021", file)
            intent.action = MediaStore.ACTION_IMAGE_CAPTURE
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
            startActivityForResult(intent, requestCodeCamera)
        }

        buttonGalery.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_PICK
            intent.type = "image/*"
            startActivityForResult(intent, requestCodeGaleria)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == requestCodeGaleria) {
            Glide
                .with(this)
                .load(data?.data)
                .circleCrop()
                .into(imageViewProfilePicture)
        } else if (requestCode == requestCodeCamera) {
            Glide
                .with(this)
                .load(fileUri)
                .circleCrop()
                .into(imageViewProfilePicture)
        }
    }
}