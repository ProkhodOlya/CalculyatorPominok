package com.example.calculyatorpominok

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var textView: TextView? = null
    private var button: Button? = null
    private var datePicker: MaterialDatePicker<Long>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById<TextView>(R.id.textView)
        button = findViewById(R.id.button)
        datePicker =  MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select date")
            .build()
        datePicker?.addOnPositiveButtonClickListener { selection ->
            val date = getDate(selection, "dd.MM.yyyy")
            textView?.text = date
        }
        button?.setOnClickListener { datePicker?.show(supportFragmentManager, "tag") }
    }
}

fun getDate(milliSeconds: Long, dateFormat: String?): String? {
    val formatter = SimpleDateFormat(dateFormat)

    val calendar: Calendar = Calendar.getInstance()
    calendar.setTimeInMillis(milliSeconds)
    return formatter.format(calendar.getTime())
}