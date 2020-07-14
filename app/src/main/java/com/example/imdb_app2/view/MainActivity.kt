package com.example.imdb_app2.view

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.imdb_app2.model.urls_type
import com.example.imdb_app2.presenter.Api_processing
import android.content.Intent
import com.example.imdb_app2.R


class MainActivity : AppCompatActivity() , Api_processing.view{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData()

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
    override fun getData() {
        var AP_obj = Api_processing()
        AP_obj.fetchAPI()
    }

    override fun get_list(url_type: urls_type): Array<String> {
        TODO("Not yet implemented")
    }
}