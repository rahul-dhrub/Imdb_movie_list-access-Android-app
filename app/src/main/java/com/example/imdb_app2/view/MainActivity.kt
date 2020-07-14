package com.example.imdb_app2.view

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.imdb_app2.model.urls_type
import com.example.imdb_app2.presenter.Api_processing
import android.content.Intent
import android.widget.Switch
import android.widget.Toast
import com.example.imdb_app2.R
import com.example.imdb_app2.model.Moviedetail


class MainActivity : AppCompatActivity(), Api_processing.view {

    var cardType: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData()

        val button_top = findViewById<Button>(R.id.button)
        val button_pop = findViewById<Button>(R.id.button2)
        val button_lat = findViewById<Button>(R.id.button3)
        val listTypeSwitch = findViewById<Switch>(R.id.switch1)

        listTypeSwitch?.setOnCheckedChangeListener { _, isChecked ->
            var message = ""
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
        button_top.setOnClickListener {
            intent = if (cardType == 1) Intent(this, recyclerView::class.java)
            else Intent(this, ListViewClass::class.java)
            intent.putExtra("list_type", urls_type.TOP)
            startActivity(intent)
        }
        button_pop.setOnClickListener {
            intent = if (cardType == 1) Intent(this, recyclerView::class.java)
            else Intent(this, ListViewClass::class.java)
            intent.putExtra("list_type", urls_type.POPULAR)
            startActivity(intent)
        }
        button_lat.setOnClickListener {
            intent = if (cardType == 1) Intent(this, recyclerView::class.java)
            else Intent(this, ListViewClass::class.java)
            intent.putExtra("list_type", urls_type.LATEST)
            startActivity(intent)
        }
    }

    override fun getData() {
        Api_processing().fetchAPI()
    }

}