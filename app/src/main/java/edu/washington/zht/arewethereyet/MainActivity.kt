package edu.washington.zht.arewethereyet

import android.widget.Toast
import android.widget.Button
import android.content.Context
import android.app.PendingIntent
import android.content.Intent
import android.telephony.PhoneNumberUtils
import android.util.Log
import android.app.AlarmManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val alarmSystem = getSystemService(Context.ALARM_SERVICE) as AlarmManager


        //------------  Get info from UI

        val startButton = findViewById<Button>(R.id.starterButton)
        val message = findViewById<EditText>(R.id.messageInput)
        val phone = findViewById<EditText>(R.id.PhoneNumberInput)
        val interval = findViewById<EditText>(R.id.IntervalInput)

        fun clickEvent() {
            var message = message.text.toString()
            var phoneNumber = phone.text.toString().replace("\\D".toRegex(), "");

            var timeInterval: Int? = interval.text.toString().toIntOrNull()

            if (timeInterval == null) {
                displayToast("Use whole numbers only")
            } else if (timeInterval == 0) {
                displayToast("Number must be greater than 0")
            } else if (message.isEmpty()) {
                displayToast("Please give a message")
            } else if (!checkFormat(phoneNumber)) {
                displayToast("Use valid phone number format")
            } else { // inputs are valid

                val intent = Intent(this, AlarmReceiver::class.java).apply {
                    putExtra("message", message)
                    putExtra("nagInterval", timeInterval)

                    putExtra("phone", phoneNumber)

                }

                if (startButton.text == "Start") {

                    val currentIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

                    var milliSecondInterval = (timeInterval * 60000).toLong()

                    Log.i("MainActivity", "Ready to set alarm on repeat: \"$message\" to $phoneNumber every $timeInterval minute(s).")
                    alarmSystem.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), milliSecondInterval, currentIntent)


                    Log.i("MainActivity", "Alarm is set.")
                    displayToast("Repeating alarm set: Sending \"$message\" to " + PhoneNumberUtils.formatNumber(phoneNumber) + " every $timeInterval minute(s).")

                    startButton.text = "Stop"

                } else {
                    startButton.text = "Start"
                    val currentIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
                    alarmSystem.cancel(currentIntent)
                    displayToast("Repeat has stopped")

                }

            }
        }

        startButton.setOnClickListener({ clickEvent() })

    }

    private fun shortenPhoneNumber(phoneNumber: String): String {
        return phoneNumber.trim().replace("[^0-9]", "")
    }

    private fun checkFormat(phoneNumber: String): Boolean {

        val digits = shortenPhoneNumber(phoneNumber)

        return digits.length in 6..12 // 6 to include short codes
    }

    private fun displayToast(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }
}

