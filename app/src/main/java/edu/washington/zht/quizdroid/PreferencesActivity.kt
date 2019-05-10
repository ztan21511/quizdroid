package edu.washington.zht.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class PreferencesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

        val saveChanges = findViewById<Button>(R.id.savechangesbutton)
        saveChanges.setOnClickListener{
            val dtr: DynamicTopicRepository = DynamicTopicRepository()
            val address : String = findViewById<EditText>(R.id.address).toString()
            dtr.updateTopicAddress(address)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
