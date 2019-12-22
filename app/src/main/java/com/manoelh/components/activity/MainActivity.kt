package com.manoelh.components.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.manoelh.components.R
import com.manoelh.components.mock.Mock
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
        loadSpinner()

    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.buttonToast)
            showToast()
        else if (id == R.id.buttomSnackbar)
            showSnackbar()
        else if (id == R.id.buttonGetSpinner)
            getValueSpinner()
        else if (id == R.id.buttonSetSpinner)
            setValueSpinner()
        else if (id == R.id.buttonProgress)
            changeVisibilityProgressBar()
    }

    private fun setListeners(){
        buttonToast.setOnClickListener(this)
        buttomSnackbar.setOnClickListener(this)
        buttonGetSpinner.setOnClickListener(this)
        buttonSetSpinner.setOnClickListener(this)
        spinnerDynamic.onItemSelectedListener = this
        buttonProgress.setOnClickListener(this)
    }

    private fun showToast(){
        /*var toast = Toast.makeText(applicationContext, "This is a toast notification", Toast.LENGTH_LONG)
        toast.view.findViewById<TextView>(android.R.id.message).setTextColor(Color.MAGENTA)
        toast.show()*/

        var toast2 = Toast.makeText(applicationContext, "", Toast.LENGTH_LONG)
        var inflater = layoutInflater.inflate(R.layout.toast_custom, null)
        toast2.view = inflater
        toast2.view.findViewById<TextView>(R.id.toastCustom).text = "Toast notification"
        toast2.show()
    }

    private fun showSnackbar(){
        var snackbar = Snackbar.make(constraintLayout, "Snackbar", Snackbar.LENGTH_LONG)

        snackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text).text = "Snackbar custom"
        snackbar.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text).setTextColor(Color.WHITE)
        snackbar.view.setBackgroundColor(Color.GRAY)
        snackbar.view.setBackgroundColor(ContextCompat.getColor(applicationContext, R.color.colorPrimaryDark))
        snackbar.setAction("Undo") {
            Snackbar.make(constraintLayout, "Undone", Snackbar.LENGTH_LONG).show()
        }
        snackbar.setActionTextColor(Color.WHITE)
        snackbar.show()
    }

    private fun loadSpinner(){
        val animals = Mock.getListAnimals()
        val adapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_dropdown_item, animals)
        spinnerDynamic.adapter = adapter
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
    }

    private fun getValueSpinner(){
        var  spinnervalue = spinnerDynamic.selectedItem
        Toast.makeText(applicationContext, "get item: $spinnervalue", Toast.LENGTH_LONG).show()
    }

    private fun setValueSpinner(){
        spinnerDynamic.setSelection(1)
    }

    private fun changeVisibilityProgressBar(){
        if (progressBar.isVisible){
            progressBar.visibility = ProgressBar.INVISIBLE
            buttonProgress.text = getString(R.string.progress)
        }
        else{
            progressBar.visibility = ProgressBar.VISIBLE
            buttonProgress.text = getString(R.string.progressStop)
            window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            textViewScreenEnable.visibility = TextView.VISIBLE
        }
    }

    override fun onBackPressed(){
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        textViewScreenEnable.visibility = TextView.INVISIBLE
    }
}
