package com.example.imdb_app2.view


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imdb_app2.R
import com.example.imdb_app2.model.Moviedetail
import com.example.imdb_app2.model.UrlsType
import com.example.imdb_app2.presenter.Api_processing
import com.example.senthil.kotlin_recyclerview.Adapter.CustomRecyclerAdapter

class recyclerView : Api_processing.view, AppCompatActivity() {
    private lateinit var passedMessage: UrlsType
    private lateinit var recyclerData: Array<Moviedetail>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title =
            (intent.getSerializableExtra("list_type") as UrlsType).type + "Movies Collection"
        actionbar.setDisplayHomeAsUpEnabled(true)


        passedMessage = intent.getSerializableExtra("list_type") as UrlsType
        getData()
        val rvRecyclerView = findViewById<RecyclerView>(R.id.sample_recyclerView)
        rvRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val adapter = CustomRecyclerAdapter(this, recyclerData)
        rvRecyclerView.adapter = adapter
    }

    override fun getData() {
        recyclerData = Api_processing().get_detail_list(passedMessage)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}