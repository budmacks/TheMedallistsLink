package au.edu.swin.sdmd.themedallists

import java.io.BufferedReader
import java.io.File
import java.io.InputStream

object CountriesList {
    var countries = mutableListOf<Country>()
    lateinit var file: BufferedReader

    //Iterates through lines of the file, splits every line into an array and uses the elements to make country objects
    fun CreateObjects() {
        file.forEachLine { line ->
            val temp = line.split(",")
            val obj = Country(temp[0], temp[1], temp[2].toInt(), temp[3].toInt(), temp[4].toInt(), temp[5].toInt())
            countries.add(obj)
        }
    }

    //Returns the length of the list of countries
    val count: Int
        get() = countries.size

}