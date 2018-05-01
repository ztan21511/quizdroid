package edu.washington.jcg25.quizdroid

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class QuestionFragment : Fragment() {

    private var current: Int? = null
    private var data: Topic? = null
    private var listener: OnToSummaryListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            current = it.getInt("current")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val q = data!!.questions[current!! - 1]

        val question : TextView = view.findViewById(R.id.questionTextView)
        question.text = q.qustionText
        val progress : TextView = view.findViewById(R.id.progressTextView)
        progress.text = "Question $current"
        val first : RadioButton = view.findViewById(R.id.choice1)
        first.text = q.answers[0]
        val second : RadioButton = view.findViewById(R.id.choice2)
        second.text = q.answers[1]
        val third : RadioButton = view.findViewById(R.id.choice3)
        third.text = q.answers[2]
        val fourth : RadioButton = view.findViewById(R.id.choice4)
        fourth.text = q.answers[3]

        val continueButton : Button = view.findViewById(R.id.continueButton)
        continueButton.isEnabled = false

        val answers: RadioGroup = view.findViewById(R.id.choicesRadioGroup)
        var selected : String? = null
        answers.setOnCheckedChangeListener { _, picked ->
            selected = view.findViewById<RadioButton>(picked).text as String
            continueButton.isEnabled = true
        }

        var correct = q.answers[q.correct - 1]
        continueButton.setOnClickListener {
            listener?.OnToSummary(selected!!, correct)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnToSummaryListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
        data = null
    }

    interface OnToSummaryListener {
        fun OnToSummary(chosen: String, correct: String)
    }

    companion object {
        @JvmStatic
        fun newInstance(passedCurrent: Int, passedData: Topic?) =
                QuestionFragment().apply {
                    current = passedCurrent
                    data = passedData
                }
    }
}
