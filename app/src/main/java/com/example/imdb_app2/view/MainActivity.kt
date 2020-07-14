package com.example.imdb_app2.view

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.imdb_app2.model.UrlsType
import com.example.imdb_app2.presenter.Api_processing
import android.content.Intent
import android.widget.Switch
import android.widget.Toast
import com.example.imdb_app2.R


class MainActivity : AppCompatActivity(), Api_processing.view {

    private var cardType: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData()

        val buttonTop = findViewById<Button>(R.id.button)
        val buttonPop = findViewById<Button>(R.id.button2)
        val buttonLat = findViewById<Button>(R.id.button3)
        val listTypeSwitch = findViewById<Switch>(R.id.switch1)

        listTypeSwitch?.setOnCheckedChangeListener { _, isChecked ->
            val message: String
            if (isChecked) {
                message = "Only Movie Name"
                cardType = 2
            } else {
                message = "Movie Info Card"
                cardType = 1
            }

            Toast.makeText(
                this@MainActivity, message,
                Toast.LENGTH_SHORT
            ).show()
        }

        var intent: Intent
        buttonTop.setOnClickListener {
            intent = if (cardType == 1) Intent(this, recyclerView::class.java)
            else Intent(this, ListViewClass::class.java)
            intent.putExtra("list_type", UrlsType.TOP)
            startActivity(intent)
        }
        buttonPop.setOnClickListener {
            intent = if (cardType == 1) Intent(this, recyclerView::class.java)
            else Intent(this, ListViewClass::class.java)
            intent.putExtra("list_type", UrlsType.POPULAR)
            startActivity(intent)
        }
        buttonLat.setOnClickListener {
            intent = if (cardType == 1) Intent(this, recyclerView::class.java)
            else Intent(this, ListViewClass::class.java)
            intent.putExtra("list_type", UrlsType.LATEST)
            startActivity(intent)
        }
    }

    override fun getData() {
        Api_processing().run(this)
    }

}