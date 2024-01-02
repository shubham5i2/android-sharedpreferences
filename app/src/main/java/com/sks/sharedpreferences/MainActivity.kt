package com.sks.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var userName: EditText
    private lateinit var userMessage: EditText
    private lateinit var counter: Button
    private lateinit var remember: CheckBox

    private var count: Int = 0
    private var name: String? = null
    private var message: String? = null
    private var isChecked: Boolean? = null

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userName = findViewById(R.id.editTextName)
        userMessage = findViewById(R.id.editTextMessage)
        counter = findViewById(R.id.button)
        remember = findViewById(R.id.checkBox)

        counter.setOnClickListener {
            count += 1
            counter.text = "" + count
        }
    }

    override fun onPause() {
        super.onPause()
        saveData()
    }

    override fun onResume() {
        super.onResume()
        retrieveData()
    }

    private fun saveData() {
        sharedPreferences = this.getSharedPreferences("saveData", Context.MODE_PRIVATE)

        name = userName.text.toString()
        message = userMessage.text.toString()
        isChecked = remember.isChecked

        val editor = sharedPreferences.edit()
        editor.putString("key name", name)
        editor.putString("key message", message)
        editor.putInt("key count", count)
        editor.putBoolean("key remember", isChecked!!)

        editor.apply()

        Toast.makeText(applicationContext, "Your data is saved", Toast.LENGTH_LONG).show()
    }

    private fun retrieveData() {
        sharedPreferences = this.getSharedPreferences("saveData", Context.MODE_PRIVATE)

        name = sharedPreferences.getString("key name", null)
        message = sharedPreferences.getString("key message", null)
        count = sharedPreferences.getInt("key count", 0)
        isChecked = sharedPreferences.getBoolean("key remember", false)

        userName.setText(name)
        userMessage.setText(message)
        counter.text = "" + count
        remember.isChecked = isChecked!!
    }
}