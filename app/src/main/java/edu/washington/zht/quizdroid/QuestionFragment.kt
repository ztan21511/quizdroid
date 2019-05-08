package edu.washington.zht.quizdroid

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
    private var data: HashMap<String, String>? = null
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

        val question : TextView = view.findViewById(R.id.questionTextView)
        question.text = data?.get("Q $current")
        val progress : TextView = view.findViewById(R.id.progressTextView)
        progress.text = "Question $current"
        val first : RadioButton = view.findViewById(R.id.choice1)
        first.text = data?.get("Q" + current + "_A1")
        val second : RadioButton = view.findViewById(R.id.choice2)
        second.text = data?.get("Q" + current + "_A2")
        val third : RadioButton = view.findViewById(R.id.choice3)
        third.text = data?.get("Q" + current + "_A3")
        val fourth : RadioButton = view.findViewById(R.id.choice4)
        fourth.text = data?.get("Q" + current + "_A4")

        val continueButton : Button = view.findViewById(R.id.continueButton)
        continueButton.isEnabled = false

        val answers: RadioGroup = view.findViewById(R.id.choicesRadioGroup)
        var selected : String? = null
        answers.setOnCheckedChangeListener { _, picked ->
            selected = view.findViewById<RadioButton>(picked).text as String
            continueButton.isEnabled = true
        }

        var correct = data?.get("Q" + current + "_correctAnswer")
        continueButton.setOnClickListener {
            listener?.OnToSummary(selected!!, correct!!)
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
        fun newInstance(passedCurrent: Int, passedData: HashMap<String, String>?) =
                QuestionFragment().apply {
                    current = passedCurrent
                    data = passedData
                }
    }
}
