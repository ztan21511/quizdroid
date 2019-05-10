package edu.washington.zht.quizdroid

import android.os.AsyncTask
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedInputStream
import java.net.HttpURLConnection
import java.net.URL

class DynamicTopicRepository : TopicRepository, AsyncTask<String, String, String> {

    private var topics =  mutableListOf<Topic>()
    private var topicDataAddress = "http://tednewardsandbox.site44.com/questions.json"

    constructor() {
        execute(topicDataAddress).get()
    }

    override fun getTopics(): List<Topic> {
        return topics
    }

    fun updateTopicAddress(newAddress : String) {
        try {
            topicDataAddress = newAddress
            execute(topicDataAddress).get()
        } catch (e: Exception) {
            topicDataAddress = "http://tednewardsandbox.site44.com/questions.json"
            e.printStackTrace()
        }
    }

    fun getTopicByName(name: String): Topic {
        for(i in 0..(topics.size - 1)){
            if(topics[i].title == name){
                return topics[i]
            }
        }
        return topics[0]
    }

    override fun doInBackground(vararg params: String?): String {
        val connection = URL(topicDataAddress).openConnection() as HttpURLConnection
        var data = ""
        topics = mutableListOf<Topic>()
        try {
            data = BufferedInputStream(connection.inputStream).use { it.reader().use { reader -> reader.readText() } }

            val results = JSONArray(data)

            for (i in 0..(results.length() - 1)) {
                val quizTopic = results[i] as JSONObject
                val quizTitle = quizTopic.getString("title")
                val quizDescription = quizTopic.getString("desc")
                val quizQuestions = quizTopic.get("questions") as JSONArray
                val questionObjects = mutableListOf<Question>()
                for (j in 0..(quizQuestions.length() - 1)) {
                    val questionObject = quizQuestions[j] as JSONObject
                    val questionText = questionObject.getString("text")
                    val questionAnswer = questionObject.getInt("answer")
                    val answersObject = questionObject.getJSONArray("answers")
                    val answers = mutableListOf<String>()
                    for (k in 0..(answersObject.length() - 1)) {
                        answers.add(answersObject[k].toString())
                    }
                    questionObjects.add(Question(questionText, answers, questionAnswer))
                }
                topics.add(Topic(quizTitle, quizDescription, questionObjects))
            }
        } finally {
            connection.disconnect()
        }
        return data
    }
}