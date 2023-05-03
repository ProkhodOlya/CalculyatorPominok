package com.calculyatorpominok.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.calculyatorpominok.mapper.mapToDayOfCommemoration
import com.calculyatorpominok.utils.ARGS_DAY_OF_COMMEMORATION
import com.calculyatorpominok.utils.DayOfCommemoration
import com.example.calculyatorpominok.R
import androidx.appcompat.widget.Toolbar

class DetailsFragment : Fragment() {
    private var textViewDateOfDeathDescription: TextView? = null
    private var textViewDateOfDeathCaption: TextView? = null
    private var toolbar: Toolbar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)

        toolbar = view.findViewById<Toolbar?>(R.id.toolbar).apply {
            setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
            setNavigationOnClickListener {
                parentFragmentManager.popBackStack()
            }
        }

        textViewDateOfDeathDescription = view.findViewById(R.id.textDateOfDeathDescription)
        textViewDateOfDeathCaption = view.findViewById(R.id.textDateOfDeathCaption)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dayOfCommemoration = arguments?.getInt(ARGS_DAY_OF_COMMEMORATION, -1) ?: -1

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
                textViewDateOfDeathDescription?.text = getString(R.string.forty_day_description)
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

    companion object {
        const val DETAILS_FRAGMENT = "detailsFragment"

        fun newInstance(dayOfCommemoration: Int) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARGS_DAY_OF_COMMEMORATION, dayOfCommemoration)
                }
            }
    }
}