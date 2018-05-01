package edu.washington.jcg25.quizdroid

import android.util.Log

class QuizApp : android.app.Application() {

    val repo = HardCodedTopicRepository()

    override fun onCreate(){
        super.onCreate()
        Log.d("QuizApp", "class loaded and running")
    }

    companion object {
        private var single: QuizApp? = null

        fun getSingleton(): QuizApp? {
            return single
        }
    }

    init {
        single = this
    }
}

