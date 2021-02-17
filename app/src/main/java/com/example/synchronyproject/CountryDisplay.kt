package com.example.synchronyproject

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import models.CountryInfo

class CountryDisplay : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_display)

        if (intent.hasExtra("COUNTRY"))
        {
            val country : CountryInfo = intent.getSerializableExtra("COUNTRY") as CountryInfo
            findViewById<TextView>(R.id.display_name).text = country.name
            findViewById<TextView>(R.id.display_capital).text = "Capital: " + country.capital
            findViewById<TextView>(R.id.display_region).text = "Region: " + country.region
            findViewById<TextView>(R.id.display_subregion).text = "Subregion: " + country.subregion
            findViewById<TextView>(R.id.display_population).text = "Population: " + country.population.toString()

            val image : ImageView = findViewById(R.id.country_image)
            if (country.region == "Americas")
            {
                image.setImageResource(R.drawable.americas)
            }
            else if (country.region == "Africa")
            {
                image.setImageResource(R.drawable.africa)
            }
            else if (country.region == "Europe")
            {
                image.setImageResource(R.drawable.europe)
            }
            else if (country.region == "Asia")
            {
                image.setImageResource(R.drawable.asia)
            }
            else if (country.region == "Oceania")
            {
                image.setImageResource(R.drawable.oceania)
            }

        }
    }
}