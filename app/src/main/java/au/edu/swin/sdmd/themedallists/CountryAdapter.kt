package au.edu.swin.sdmd.themedallists

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.text.style.BackgroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextClock
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.edit
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.NonDisposableHandle.parent
import java.io.File
import java.io.FileOutputStream

class CountryAdapter(val sharedPref : SharedPreferences) : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    //Sets the view to a the row layout and uses the view to return a view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.row_layout, parent, false) as View
        return ViewHolder(view)
    }

    //Gets the total number of countries by list size
    override fun getItemCount(): Int = CountriesList.count

    //Uses the passes in the view holder index to the country list and passes the data into the bind function
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = CountriesList.countries.get(position)
        holder.bind(item, sharedPref)
    }

    //Defines the characteristics of a view
    class ViewHolder(private val v : View) : RecyclerView.ViewHolder(v) {
       //Accesses all widgets in row layout
        private val countryName : TextView = v.findViewById(R.id.countryName)
        private val countryCode : TextView = v.findViewById(R.id.countryCode)
        private val medals : TextView = v.findViewById(R.id.medals)

        fun bind(item : Country, sharedPref : SharedPreferences) {
            //Sets text of widgets to corresponding data
            countryName.text = item.countryName
            countryCode.text = item.countryCode
            val totalMedals = item.gold + item.silver + item.Bronze
            medals.text = totalMedals.toString()
            //Sets the background to grey if medal tally exceeds 500
            if (totalMedals > 500) {
                v.setBackgroundColor(R.color.medgrey)
            } else {
                v.setBackgroundColor(android.R.color.transparent)
            }
            //Displays a pop up message when clicked and saves the country name and code to shared preferences
            v.setOnClickListener{
                val toast = Toast.makeText(it.context, "${item.countryName} has won ${item.gold} gold medals", Toast.LENGTH_LONG)
                toast.show()
                sharedPref.edit() {
                    putString("lastVisited", "${item.countryName} (${item.countryCode})")
                }

            }
        }
    }
}