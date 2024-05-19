package au.edu.swin.sdmd.themedallists

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Collects country name and code from intent
        val lastVisited = intent.getStringExtra("lastVisited")

        //Accesses text widget from layout
        val textWidget = findViewById<TextView>(R.id.lastClicked)

        //Displays the country name and code encased in message
        textWidget.text = "The country that was last clicked was ${lastVisited}"
    }
}