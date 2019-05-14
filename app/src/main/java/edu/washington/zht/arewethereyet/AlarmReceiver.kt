package edu.washington.zht.arewethereyet

import android.telephony.PhoneNumberUtils
import android.util.Log
import android.widget.Toast

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.Context


class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(message: Context?, intent: Intent?) {
        Log.i("Alarm", "Received alarm.")

        val phoneInput = intent?.getStringExtra("phone")
        val messageInput = intent?.getStringExtra("message")

        var phoneNumber = PhoneNumberUtils.formatNumber(phoneInput)
        Toast.makeText(message, "$phoneNumber: $messageInput", Toast.LENGTH_SHORT).show()
    }

}