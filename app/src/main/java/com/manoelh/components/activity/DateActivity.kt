package com.manoelh.components.activity

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.TimePicker.OnTimeChangedListener
import android.widget.Toast
import com.manoelh.components.R
import kotlinx.android.synthetic.main.activity_date.*
import java.text.SimpleDateFormat
import java.util.*

class DateActivity : AppCompatActivity(), View.OnClickListener, DatePickerDialog.OnDateSetListener,
    OnTimeChangedListener{

    private val mSimpleFormat = SimpleDateFormat("MM/dd/yyyy")
    private var calendar = Calendar.getInstance()
    private var hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
    private var minute = calendar.get(Calendar.MINUTE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date)
        setListeners()
    }

    private fun setListeners(){
        imageViewPreviousActivity.setOnClickListener(this)
        buttonDatePicker.setOnClickListener(this)
        buttonGetTimePicker.setOnClickListener(this)
        buttonSetTimePicker.setOnClickListener(this)
        timePicker.setOnTimeChangedListener(this)
    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.buttonDatePicker)
            openDatePicker()
        else if (id == R.id.buttonGetTimePicker)
            getTime()
        else if (id == R.id.buttonSetTimePicker)
            setTime()
        else if (id == R.id.imageViewPreviousActivity)
            openMainActivity()
    }

    private fun openDatePicker(){
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(this, this, year, month, day)
        datePickerDialog.show()
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val date = mSimpleFormat.format(calendar.time)
        buttonDatePicker.text = date
    }

    override fun onTimeChanged(view: TimePicker, hourOfDay: Int, minute: Int) {
        this.hourOfDay = hourOfDay
        this.minute = minute
    }

    private fun getTime(){
        Toast.makeText(this, "Time: $hourOfDay:$minute", Toast.LENGTH_LONG).show()
    }

    private fun setTime(){
        if (Build.VERSION.SDK_INT >= 23)
            timePicker.hour = 20
        else
            timePicker.currentHour = 20
    }

    private fun openMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
