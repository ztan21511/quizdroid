package edu.washington.jcg25.quizdroid

class HardCodedTopicRepository : TopicRepository {

    private var topics =  mutableListOf<Topic>()

    override fun getTopics(): List<Topic> {
        return topics
    }

    /*init {
        var math : Topic = Topic(
                "Math",
                "Math-y questions",
                "Questions about math, numbers, and logic",
                listOf(Question(
                        "Question 1",
                        listOf("Answer 1", "Answer 2", "Answer 3", "Answer 4"),
                        1
                ), Question(
                        "Question 2",
                        listOf("Answer 1", "Answer 2", "Answer 3", "Answer 4"),
                        2
                ), Question(
                        "Question 3",
                        listOf("Answer 1", "Answer 2", "Answer 3", "Answer 4"),
                        3
                ), Question(
                        "Question 4",
                        listOf("Answer 1", "Answer 2", "Answer 3", "Answer 4"),
                        4
                )
                )
        )

        var physics : Topic = Topic(
                "Physics",
                "Questions about physics",
                "Questions about Newton and forces and stuff like that",
                listOf(Question(
                        "Question 1",
                        listOf("Answer 1", "Answer 2", "Answer 3", "Answer 4"),
                        1
                ), Question(
                        "Question 2",
                        listOf("Answer 1", "Answer 2", "Answer 3", "Answer 4"),
                        2
                ), Question(
                        "Question 3",
                        listOf("Answer 1", "Answer 2", "Answer 3", "Answer 4"),
                        3
                ), Question(
                        "Question 4",
                        listOf("Answer 1", "Answer 2", "Answer 3", "Answer 4"),
                        4
                )
                )
        )

        var marvel : Topic = Topic(
                "Marvel Super Heroes",
                "Questions about Marvel Super Heroes",
                "Iron Man, Thor, Hulk. How much do you really know about your heroes?",
                listOf(Question(
                        "Question 1",
                        listOf("Answer 1", "Answer 2", "Answer 3", "Answer 4"),
                        1
                ), Question(
                        "Question 2",
                        listOf("Answer 1", "Answer 2", "Answer 3", "Answer 4"),
                        2
                ), Question(
                        "Question 3",
                        listOf("Answer 1", "Answer 2", "Answer 3", "Answer 4"),
                        3
                ), Question(
                        "Question 4",
                        listOf("Answer 1", "Answer 2", "Answer 3", "Answer 4"),
                        4
                )
                )
        )

        topics.add(math)
        topics.add(physics)
        topics.add(marvel)
    }*/
}