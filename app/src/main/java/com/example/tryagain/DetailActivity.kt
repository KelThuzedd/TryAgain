package com.example.tryagain

import android.widget.ImageView
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val id = intent.getStringExtra("id")
        val name = intent.getStringExtra("name")
        val description = intent.getStringExtra("description")
        val imageName = intent.getStringExtra("imageName")

        val idTextView = findViewById<TextView>(R.id.idTextView)
        val nameTextView = findViewById<TextView>(R.id.nameTextView)
        val descriptionTextView = findViewById<TextView>(R.id.descriptionTextView)
        val imageView = findViewById<ImageView>(R.id.imageView)

        idTextView.text = id
        nameTextView.text = name
        descriptionTextView.text = description

        val imageResourceId = if (!imageName.isNullOrBlank()) {
            val resourceId = resources.getIdentifier(imageName, "drawable", packageName)
            if (resourceId != 0) {
                resourceId
            } else {
                R.drawable.default_image
            }
        } else {
            R.drawable.default_image
        }
        imageView.setImageResource(imageResourceId)
    }
}
