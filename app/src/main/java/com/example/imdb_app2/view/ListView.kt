package com.example.imdb_app2.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.imdb_app2.R
import com.example.imdb_app2.model.urls_type
import com.example.imdb_app2.presenter.Api_processing

class ListViewClass : Api_processing.view ,  AppCompatActivity() {
   private lateinit var array :Array<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)
        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = (intent.getSerializableExtra("list_type") as urls_type).type + "Movies List"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        array = get_list(intent.getSerializableExtra("list_type") as urls_type)

        val adapter = ArrayAdapter(this,
            R.layout.adapter_content, array)

        val listView: ListView = findViewById(R.id.listview_1)
        listView.setAdapter(adapter)

        listView.onItemClickListener = object : AdapterView.OnItemClickListener {

            override fun onItemClick(parent: AdapterView<*>, view: View,
                                     position: Int, id: Long) {

                // value of item that is clicked
                val itemValue = listView.getItemAtPosition(position) as String

                // Toast the values
                Toast.makeText(applicationContext,
                    "Position :$position\nItem Value : $itemValue", Toast.LENGTH_LONG)
                    .show()
            }
        }

    }

    override fun get_list(url_type: urls_type) : Array<String> {
       var Ap = Api_processing()
        return Ap.get_name_list(url_type).toTypedArray()
    }

    override fun getData() {
        TODO("Not yet implemented")
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}