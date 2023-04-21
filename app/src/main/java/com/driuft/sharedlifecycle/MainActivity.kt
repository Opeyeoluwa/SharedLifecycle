package com.driuft.sharedlifecycle

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var _sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _sharedPreferences = this.getSharedPreferences(this.packageName, Context.MODE_PRIVATE)
        _sharedPreferences.registerOnSharedPreferenceChangeListener(listener)

        with (_sharedPreferences.edit()) {
            putString(getString(R.string.current_lifecycle_state), "onCreate")
            apply()
        }

    }

    private val listener =
        SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
            Toast.makeText(
                this,
                sharedPreferences.getString(
                    key,
                    resources.getString(R.string.default_state)
                ),
                Toast.LENGTH_SHORT
            ).show()
        }

    private fun unregisterListener() {
        _sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
    }

    override fun onStart() {
        super.onStart()
        _sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
        with (_sharedPreferences.edit()) {
            putString(getString(R.string.current_lifecycle_state), "onStart")
            apply()
        }
    }

    override fun onResume() {
        super.onResume()
        _sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
        with (_sharedPreferences.edit()) {
            putString(getString(R.string.current_lifecycle_state), "onResume")
            apply()
        }
    }

    override fun onPause() {
        super.onPause()
        _sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
        with (_sharedPreferences.edit()) {
            putString(getString(R.string.current_lifecycle_state), "onPause")
            apply()
        }
    }

    override fun onStop() {
        super.onStop()
        _sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
        with (_sharedPreferences.edit()) {
            putString(getString(R.string.current_lifecycle_state), "onStop")
            apply()
        }
    }

    override fun onRestart() {
        super.onRestart()
        _sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
        with (_sharedPreferences.edit()) {
            putString(getString(R.string.current_lifecycle_state), "onRestart")
            apply()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        with (_sharedPreferences.edit()) {
            putString(getString(R.string.current_lifecycle_state), "onDestroy")
            apply()
        }
        unregisterListener()
    }
}