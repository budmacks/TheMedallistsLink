package au.edu.swin.sdmd.themedallists

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        //Setting up the Countries object by reading in the file
        val countryList = findViewById<RecyclerView>(R.id.countryView)
        CountriesList.file = resources.openRawResource(R.raw.medallists).bufferedReader()
        CountriesList.CreateObjects()

        //Instantiating an adapter and a layout manager class
        countryList.adapter = CountryAdapter(this.getPreferences(Context.MODE_PRIVATE))
        countryList.layoutManager = LinearLayoutManager(this)

        //Accesses last visited from shared preferences and sends the value to Detail Activity via intent
        val book = findViewById<ImageView>(R.id.book)
        book.setOnClickListener() {
            val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
            val lastVisit = sharedPref.getString("lastVisited", "Australia").toString()
            intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("lastVisited", lastVisit)
            startActivity(intent)
        }


    }
}