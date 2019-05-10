package edu.washington.zht.quizdroid

interface TopicRepository {
    fun getTopics() : List<Topic>
}