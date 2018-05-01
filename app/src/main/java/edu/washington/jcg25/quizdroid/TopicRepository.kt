package edu.washington.jcg25.quizdroid

interface TopicRepository {
    fun getTopics() : List<Topic>
}