package edu.washington.zht.quizdroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.*

class MainActivity : AppCompatActivity() {

    val categories = arrayOf("Math", "Physics", "Marvel Super Heroes")
    val description = arrayOf("Math-y Stuff", "Physics topics, like mechanics and waves", "Marvel Super Heroes, does this really need a description?")
    val numQuestions = arrayOf(5,4,3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.categorylist)
        val adapter = ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, categories)

        listView.adapter = adapter

        listView.setOnItemClickListener({parent, v, position, id ->
            var intent : Intent = Intent(this, SecondActivity::class.java)
                    .putExtra("category", categories[position])
                    .putExtra("description", description[position])
                    .putExtra("numQuestions", numQuestions[position])

            startActivity(intent)

        })
    }
}

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val bundle : Bundle = intent.extras
        val category : String = bundle.getString("category")
        val description : String = bundle.getString("description")
        val numQuestions : Int = bundle.getInt("numQuestions")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_main)

        val overviewText = findViewById<TextView>(R.id.overviewTextView)
        overviewText.text = "$category Overview"

        val descriptionText = findViewById<TextView>(R.id.descriptionTextView)
        descriptionText.text = description

        val numQuestionsText = findViewById<TextView>(R.id.numQuestionsTextView)
        numQuestionsText.text = "This quiz has $numQuestions questions"

        val beginButton = findViewById<Button>(R.id.beginButton)
        beginButton.setOnClickListener { view ->
            var intent : Intent = Intent(this, ThirdActivity::class.java)
                    .putExtra("totalQuestions", numQuestions)
                    .putExtra("currentQuestion", 1)
                    .putExtra("totalCorrect", 0)

            startActivity(intent)
        }


    }
}


class ThirdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val bundle : Bundle = intent.extras
        val total : Int = bundle.getInt("totalQuestions")
        val current : Int = bundle.getInt("currentQuestion")
        val correct : Int = bundle.getInt("totalCorrect")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.third_main)

        val continueButton = findViewById<Button>(R.id.continueButton)
        continueButton.isEnabled = false

        val choices = findViewById<RadioGroup>(R.id.choicesRadioGroup)
        choices.setOnCheckedChangeListener { _, _ ->
            continueButton.isEnabled = true
        }

        val progressTextView = findViewById<TextView>(R.id.progressTextView)
        progressTextView.text = "$current/$total"

        val questionTextView = findViewById<TextView>(R.id.questionTextView)
        questionTextView.text = "Question $current"


        continueButton.setOnClickListener { view ->
            //if correct, correct++
            var intent : Intent = Intent(this, FourthActivity::class.java)
                    .putExtra("totalQuestions", total)
                    .putExtra("currentQuestion", current)
                    .putExtra("totalCorrect", correct)
                    .putExtra("correctAnswer", "unknown")
                    .putExtra("selectedAnswer", findViewById<RadioButton>(choices.checkedRadioButtonId).text)

            startActivity(intent)
        }
    }


}

class FourthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val bundle : Bundle = intent.extras
        val total : Int = bundle.getInt("totalQuestions")
        val current : Int = bundle.getInt("currentQuestion")
        val correct : Int = bundle.getInt("totalCorrect")
        val guess : String = bundle.getString("selectedAnswer")
        val answer : String = bundle.getString("correctAnswer")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.fourth_main)

        val nextButton = findViewById<Button>(R.id.continueButton)

        val progressTextView = findViewById<TextView>(R.id.progressTextView)
        progressTextView.text = "$current/$total"

        val correctTextView = findViewById<TextView>(R.id.correctTextView)
        correctTextView.text = "The correct answer was $answer"

        val selectedTextView = findViewById<TextView>(R.id.selectedTextView)
        selectedTextView.text = "You selected $guess"

        val numCorrectTextView = findViewById<TextView>(R.id.numCorrectTextView)
        numCorrectTextView.text = "You've gotten $correct out of $current correct so far"

        if(total == current){
            nextButton.text = "Finish"
            nextButton.setOnClickListener { view ->
                var intent : Intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        } else {
            nextButton.setOnClickListener { view ->
                var intent: Intent = Intent(this, ThirdActivity::class.java)
                        .putExtra("totalQuestions", total)
                        .putExtra("currentQuestion", current + 1)
                        .putExtra("totalCorrect", correct)
                startActivity(intent)
            }
        }
    }
}