package com.example.calculyatorpominok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import com.google.android.material.datepicker.SingleDateSelector

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
        datePicker?.addOnPositiveButtonClickListener(object : MaterialPickerOnPositiveButtonClickListener<Long>{
            override fun onPositiveButtonClick(selection: Long?) {
                textView?.setText(selection.toString())
            }

        })
        button?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
               datePicker?.show(supportFragmentManager, "tag")
            }
        })
    }
}