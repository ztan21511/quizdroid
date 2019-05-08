package edu.washington.zht.quizdroid

import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle


class QuizActivity : AppCompatActivity(), TopicOverviewFragment.OnStartQuizListener, QuestionFragment.OnToSummaryListener, SummaryFragment.OnNextQuestionListener {

    var current : Int = 0
    var correct :Int = 0
    var data : HashMap<String, String>? = null
    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val bundle : Bundle = intent.extras
        val category : String = bundle.getString("category")


        data = when(category){
            "Math" -> mathData
            "Physics" -> physicsData
            "Marvel Super Heroes" -> marvelSupersData
            else -> {
                marvelSupersData
            }
        }

        var fragment: Fragment? = null
        if(current == 0){
            fragment = TopicOverviewFragment.newInstance(data)
        }

        if (null != fragment) {
            val ft = fragmentManager.beginTransaction()
            ft.replace(R.id.root, fragment)
            ft.commit()
        }


    }

    override fun OnStartQuiz() {
        current++

        val fragment: QuestionFragment = QuestionFragment.newInstance(current, data)
        val ft = fragmentManager.beginTransaction()
        ft.replace(R.id.root, fragment as Fragment)
        ft.commit()
    }

    override fun OnToSummary(selected : String, right : String) {
        if (selected == right) {
            correct++
        }

        val fragment: SummaryFragment? = SummaryFragment.newInstance(selected, right, correct, current, data)
        val ft = fragmentManager.beginTransaction()
        ft.replace(R.id.root, fragment)
        ft.commit()
    }

    override fun OnNextQuestion(){
        if (current.toString() == data!!["numQuestions"]) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            OnStartQuiz()
        }
    }

    companion object {
        private val mathData = hashMapOf(
                "name" to "Math",
                "description" to "Description goes here",
                "numQuestions" to "4",
                "Q1" to "Question goes here",
                "Q1_A1" to "Answer 1",
                "Q1_A2" to "Answer 2",
                "Q1_A3" to "Answer 3",
                "Q1_A4" to "Answer 4",
                "Q1_correctAnswer" to "Answer 1",
                "Q2" to "Question goes here",
                "Q2_A1" to "Answer 1",
                "Q2_A2" to "Answer 2",
                "Q2_A3" to "Answer 3",
                "Q2_A4" to "Answer 4",
                "Q2_correctAnswer" to "Answer 2",
                "Q3" to "Question goes here",
                "Q3_A1" to "Answer 1",
                "Q3_A2" to "Answer 2",
                "Q3_A3" to "Answer 3",
                "Q3_A4" to "Answer 4",
                "Q3_correctAnswer" to "Answer 3",
                "Q4" to "Question goes here",
                "Q4_A1" to "Answer 1",
                "Q4_A2" to "Answer 2",
                "Q4_A3" to "Answer 3",
                "Q4_A4" to "Answer 4",
                "Q4_correctAnswer" to "Answer 4"
        )

        private val physicsData = hashMapOf(
                "name" to "Physics",
                "description" to "Description goes here",
                "numQuestions" to "4",
                "Q1" to "Question goes here",
                "Q1_A1" to "Answer 1",
                "Q1_A2" to "Answer 2",
                "Q1_A3" to "Answer 3",
                "Q1_A4" to "Answer 4",
                "Q1_correctAnswer" to "Answer 1",
                "Q2" to "Question goes here",
                "Q2_A1" to "Answer 1",
                "Q2_A2" to "Answer 2",
                "Q2_A3" to "Answer 3",
                "Q2_A4" to "Answer 4",
                "Q2_correctAnswer" to "Answer 2",
                "Q3" to "Question goes here",
                "Q3_A1" to "Answer 1",
                "Q3_A2" to "Answer 2",
                "Q3_A3" to "Answer 3",
                "Q3_A4" to "Answer 4",
                "Q3_correctAnswer" to "Answer 3",
                "Q4" to "Question goes here",
                "Q4_A1" to "Answer 1",
                "Q4_A2" to "Answer 2",
                "Q4_A3" to "Answer 3",
                "Q4_A4" to "Answer 4",
                "Q4_correctAnswer" to "Answer 4"
        )
        private val marvelSupersData = hashMapOf(
                "name" to "Marvel Super Guys",
                "description" to "Description goes here",
                "numQuestions" to "4",
                "Q1" to "Question goes here",
                "Q1_A1" to "Answer 1",
                "Q1_A2" to "Answer 2",
                "Q1_A3" to "Answer 3",
                "Q1_A4" to "Answer 4",
                "Q1_correctAnswer" to "Answer 1",
                "Q2" to "Question goes here",
                "Q2_A1" to "Answer 1",
                "Q2_A2" to "Answer 2",
                "Q2_A3" to "Answer 3",
                "Q2_A4" to "Answer 4",
                "Q2_correctAnswer" to "Answer 2",
                "Q3" to "Question goes here",
                "Q3_A1" to "Answer 1",
                "Q3_A2" to "Answer 2",
                "Q3_A3" to "Answer 3",
                "Q3_A4" to "Answer 4",
                "Q3_correctAnswer" to "Answer 3",
                "Q4" to "Question goes here",
                "Q4_A1" to "Answer 1",
                "Q4_A2" to "Answer 2",
                "Q4_A3" to "Answer 3",
                "Q4_A4" to "Answer 4",
                "Q4_correctAnswer" to "Answer 4"
        )
    }
}
