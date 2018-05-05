package edu.washington.jcg25.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.ArrayAdapter
import android.widget.ListView
import android.view.MenuItem


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val topicData = DynamicTopicRepository().getTopics()

        val categories = listOf(topicData[0].title, topicData[1].title, topicData[2].title)
        val listView = findViewById<ListView>(R.id.ListViewTopics) as ListView

        val adapter = ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, categories)

        listView.adapter = adapter

        listView.setOnItemClickListener({_, _, position, _ ->
            var intent : Intent = Intent(this, QuizActivity::class.java)
                    .putExtra("category", categories[position])

            startActivity(intent)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.preferences, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val intent = Intent(this, PreferencesActivity::class.java)
        startActivity(intent)
        return true
    }

}
