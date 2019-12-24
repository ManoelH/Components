package com.manoelh.components.activity

import android.content.Intent
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

class MainActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener,
    SeekBar.OnSeekBarChangeListener {

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
        else if (id == R.id.buttonSnackbar)
            showSnackbar()
        else if (id == R.id.buttonGetSpinner)
            getValueSpinner()
        else if (id == R.id.buttonSetSpinner)
            setValueSpinner()
        else if (id == R.id.buttonProgress)
            changeVisibilityProgressBar()
        else if (id == R.id.buttonGetSeekBar)
            getValueSeekbar()
        else if (id == R.id.buttonSetSeekBar)
            setValueSeekbar()
        else if (id == R.id.imageViewNextActivity)
            openDateActivity()
    }

    private fun setListeners(){
        buttonToast.setOnClickListener(this)
        buttonSnackbar.setOnClickListener(this)
        buttonGetSpinner.setOnClickListener(this)
        buttonSetSpinner.setOnClickListener(this)
        buttonProgress.setOnClickListener(this)
        buttonGetSeekBar.setOnClickListener(this)
        buttonSetSeekBar.setOnClickListener(this)
        imageViewNextActivity.setOnClickListener(this)
        spinnerDynamic.onItemSelectedListener = this
        seekBar.setOnSeekBarChangeListener(this)
    }

    private fun showToast(){
        /*var toast = Toast.makeText(this, "This is a toast notification", Toast.LENGTH_LONG)
        toast.view.findViewById<TextView>(android.R.id.message).setTextColor(Color.MAGENTA)
        toast.show()*/

        var toast2 = Toast.makeText(this, "", Toast.LENGTH_LONG)
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
        snackbar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark))
        snackbar.setAction("Undo") {
            Snackbar.make(constraintLayout, "Undone", Snackbar.LENGTH_LONG).show()
        }
        snackbar.setActionTextColor(Color.WHITE)
        snackbar.show()
    }

    private fun loadSpinner(){
        val animals = Mock.getListAnimals()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, animals)
        spinnerDynamic.adapter = adapter
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        val id = view.id
        if (id == R.id.spinnerDynamic){
        //TODO someting implementation
        }
    }

    private fun getValueSpinner(){
        val  spinnervalue = spinnerDynamic.selectedItem
        Toast.makeText(this, "get item: $spinnervalue", Toast.LENGTH_LONG).show()
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

    private fun getValueSeekbar(){
        Toast.makeText(this, "Get seekbar, volume: ${seekBar.progress}", Toast.LENGTH_LONG).show()
    }

    private fun setValueSeekbar(){
        seekBar.progress += 10
        textViewSeekBar.text = "volume: ${seekBar.progress}"
    }

    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        val id = seekBar.id
        if (id == R.id.seekBar)
            textViewSeekBar.text = "volume: $progress"
    }

    override fun onStartTrackingTouch(seekBar: SeekBar) {
    }

    override fun onStopTrackingTouch(seekBar: SeekBar) {
    }

    private fun openDateActivity(){
        val intent = Intent(this, DateActivity::class.java)
        startActivity(intent)
    }
}
