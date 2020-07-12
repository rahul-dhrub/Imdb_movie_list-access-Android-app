package com.example.imdb_app2

import android.os.Bundle
import android.view.Menu
import android.widget.Button
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.imdb_app2.model.api_urls
import com.example.imdb_app2.model.urls_type
import com.example.imdb_app2.presenter.Api_processing
import android.content.Intent
import android.widget.ListView
import android.widget.Toast
import com.example.imdb_app2.view.ListViewClass


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ///////api request made
        var AP_obj = Api_processing()
        AP_obj.run(urls_type.POPULAR)
        AP_obj.run(urls_type.TOP)
        AP_obj.run(urls_type.LATEST)

        val button_top = findViewById<Button>(R.id.button)
        val button_pop = findViewById<Button>(R.id.button2)
        val button_lat = findViewById<Button>(R.id.button3)

        button_top.setOnClickListener {
            val intent = Intent(this, ListViewClass::class.java)
            intent.putExtra("list_type" , urls_type.TOP)
            startActivity(intent)
        }

        button_pop.setOnClickListener {
            val intent = Intent(this, ListViewClass::class.java)
            intent.putExtra("list_type" , urls_type.POPULAR)
            startActivity(intent)
        }

        button_lat.setOnClickListener {
            val intent = Intent(this, ListViewClass::class.java)
            intent.putExtra("list_type" , urls_type.LATEST)
            startActivity(intent)
        }

    }
}