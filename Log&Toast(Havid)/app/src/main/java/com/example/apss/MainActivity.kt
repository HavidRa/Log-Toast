package com.example.apss

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import android.util.Log

class MainActivity : AppCompatActivity() {

    private lateinit var tvSelectedDateTime: TextView
    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.v("cekStringKirim", "Halo")

        Toast.makeText(applicationContext, "Halo Havid", Toast.LENGTH_SHORT).show()

        tvSelectedDateTime = findViewById(R.id.tvSelectedDateTime)

        // Date Picker
        findViewById<Button>(R.id.btnDatePicker).setOnClickListener {
            showDatePicker()
        }

        // Time Picker
        findViewById<Button>(R.id.btnTimePicker).setOnClickListener {
            showTimePicker()
        }

        // Alert Dialog
        findViewById<Button>(R.id.btnShowAlert).setOnClickListener {
            showAlertDialog()
        }
    }

    private fun showDatePicker() {
        val datePicker = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateSelectedDateTime()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }

    private fun showTimePicker() {
        val timePicker = TimePickerDialog(
            this,
            { _, hourOfDay, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)
                updateSelectedDateTime()
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        )
        timePicker.show()
    }

    private fun updateSelectedDateTime() {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())
        val formattedDateTime = dateFormat.format(calendar.time)
        tvSelectedDateTime.text = "Tanggal dan waktu yang dipilih: $formattedDateTime"
        Log.d("MainActivity", "Tanggal dan waktu diperbarui: $formattedDateTime")

    }

    private fun showAlertDialog() {
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Konfirmasi")
            .setMessage("Apakah Anda yakin dengan pilihan tanggal dan waktu?")
            .setPositiveButton("Ya") { _: DialogInterface, _: Int ->
                Toast.makeText(applicationContext, "Pilihan dikonfirmasi", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Tidak") { _: DialogInterface, _: Int ->
                Toast.makeText(applicationContext, "Pilihan dibatalkan", Toast.LENGTH_SHORT).show()
            }
            .setNeutralButton("Nanti") { _: DialogInterface, _: Int ->
                Toast.makeText(applicationContext, "Anda memilih nanti", Toast.LENGTH_SHORT).show()
            }
            .create()

        alertDialog.show()
    }
}