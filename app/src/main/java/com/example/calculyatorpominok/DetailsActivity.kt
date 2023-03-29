package com.example.calculyatorpominok

import android.graphics.NinePatch
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.calculyatorpominok.mapper.mapToDayOfCommemoration
import com.example.calculyatorpominok.utils.THREE_DAY_DESCRIPTION
import com.example.calculyatorpominok.utils.NINE_DAY_DESCRIPTION
import com.example.calculyatorpominok.utils.ARGS_DAY_OF_COMMEMORATION
import com.example.calculyatorpominok.utils.DayOfCommemoration
import com.example.calculyatorpominok.utils.FORTY_DAY_DESCRIPTION
import com.example.calculyatorpominok.utils.ONE_YEAR_DESCRIPTION
import com.example.calculyatorpominok.utils.SIX_MONTH_DESCRIPTION


class DetailsActivity : AppCompatActivity() {
    private var textViewDateOfDeathDescription: TextView? = null
    private var textViewDateOfDeathCaption: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        textViewDateOfDeathDescription = findViewById(R.id.textDateOfDeathDescription)
        textViewDateOfDeathCaption = findViewById(R.id.textDateOfDeathCaption)
        val dayOfCommemoration = intent.getIntExtra(ARGS_DAY_OF_COMMEMORATION, -1)

        when (dayOfCommemoration.mapToDayOfCommemoration()) {
            DayOfCommemoration.THREE_DAY -> {
                textViewDateOfDeathCaption?.text = getString(R.string.date_of_death_3day_caption)
                textViewDateOfDeathDescription?.text = THREE_DAY_DESCRIPTION
            }
            DayOfCommemoration.NINE_DAY -> {
                textViewDateOfDeathCaption?.text = getString(R.string.date_of_death_9day_caption)
                textViewDateOfDeathDescription?.text = NINE_DAY_DESCRIPTION
            }
            DayOfCommemoration.FORTY_DAY -> {
                textViewDateOfDeathCaption?.text = getString(R.string.date_of_deatg_40day_caption)
                textViewDateOfDeathDescription?.text = FORTY_DAY_DESCRIPTION
            }
            DayOfCommemoration.SIX_MONTH -> {
                textViewDateOfDeathCaption?.text = getString(R.string.date_of_death_6month_caption)
                textViewDateOfDeathDescription?.text = SIX_MONTH_DESCRIPTION
            }
            DayOfCommemoration.ONE_YEAR -> {
                textViewDateOfDeathCaption?.text = getString(R.string.date_of_death_1year_caption)
                textViewDateOfDeathDescription?.text = ONE_YEAR_DESCRIPTION
            }
            null -> TODO()
        }

    }
}