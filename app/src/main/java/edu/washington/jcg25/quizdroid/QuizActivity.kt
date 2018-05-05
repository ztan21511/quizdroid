package edu.washington.jcg25.quizdroid

import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle


class QuizActivity : AppCompatActivity(),
        TopicOverviewFragment.OnStartQuizListener,
        QuestionFragment.OnToSummaryListener,
        SummaryFragment.OnNextQuestionListener {

    var current : Int = 0
    var correct : Int = 0
    var data : Topic? = null
    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val bundle : Bundle = intent.extras
        val category : String = bundle.getString("category")


        data = DynamicTopicRepository().getTopicByName(category)

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
        if (current == data!!.questions.size) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            OnStartQuiz()
        }
    }
}
