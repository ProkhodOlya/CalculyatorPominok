package com.calculyatorpominok.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.calculyatorpominok.R
import com.calculyatorpominok.mapper.mapToDayOfCommemoration
import com.calculyatorpominok.utils.ARGS_DAY_OF_COMMEMORATION
import com.calculyatorpominok.utils.DayOfCommemoration
import com.google.android.material.appbar.MaterialToolbar


class DetailsActivity : AppCompatActivity() {
    private var textViewDateOfDeathDescription: TextView? = null
    private var textViewDateOfDeathCaption: TextView? = null
    private var topAppBar: MaterialToolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        topAppBar = findViewById(R.id.toolbar)
        setSupportActionBar(topAppBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        topAppBar?.setNavigationOnClickListener {
            finish()
        }
        textViewDateOfDeathDescription = findViewById(R.id.textDateOfDeathDescription)
        textViewDateOfDeathCaption = findViewById(R.id.textDateOfDeathCaption)
        val dayOfCommemoration = intent.getIntExtra(ARGS_DAY_OF_COMMEMORATION, -1)

        when (dayOfCommemoration.mapToDayOfCommemoration()) {
            DayOfCommemoration.THREE_DAY -> {
                textViewDateOfDeathCaption?.text = getString(R.string.date_of_death_3day_caption)
                textViewDateOfDeathDescription?.text = getString(R.string.three_day_description)
            }
            DayOfCommemoration.NINE_DAY -> {
                textViewDateOfDeathCaption?.text = getString(R.string.date_of_death_9day_caption)
                textViewDateOfDeathDescription?.text = getString(R.string.nine_day_description)
            }
            DayOfCommemoration.FORTY_DAY -> {
                textViewDateOfDeathCaption?.text = getString(R.string.date_of_deatg_40day_caption)
                textViewDateOfDeathDescription?.text =  getString(R.string.forty_day_description)
            }
            DayOfCommemoration.SIX_MONTH -> {
                textViewDateOfDeathCaption?.text = getString(R.string.date_of_death_6month_caption)
                textViewDateOfDeathDescription?.text = getString(R.string.six_month_description)
            }
            DayOfCommemoration.ONE_YEAR -> {
                textViewDateOfDeathCaption?.text = getString(R.string.date_of_death_1year_caption)
                textViewDateOfDeathDescription?.text = getString(R.string.one_year_description)
            }
            null -> {
                textViewDateOfDeathCaption?.text = getString(R.string.error)
                textViewDateOfDeathDescription?.text = getString(R.string.error)
            }
        }

    }
}