package edu.washington.zht.quizdroid

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class SummaryFragment : Fragment() {
    var chosen : String? = null
    var right : String? = null
    var totalCorrect : Int? = null
    var current : Int? = null
    var data : HashMap<String, String>? = null
    var listener : OnNextQuestionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            chosen = it.getString("chosen")
            right = it.getString("right")
            totalCorrect = it.getInt("correct")
            current = it.getInt("current")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_summary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressView : TextView = view.findViewById(R.id.progressTextView)
        progressView.text = "Question $current"
        val correctText: TextView = view.findViewById(R.id.correctTextView)
        correctText.text = "The correct answer was $right"
        val selectedText : TextView = view.findViewById(R.id.selectedTextView)
        selectedText.text = "You selected $chosen"
        val gitgood : TextView = view.findViewById(R.id.numCorrectTextView)
        val total : String? = data!!["numQuestions"]
        gitgood.text = "You have gotten $totalCorrect correct out of $current so far!"

        val continueButton : Button = view.findViewById(R.id.continueButton)
        if (current.toString() == total) {
            continueButton.text = "Finish"
        }

        continueButton.setOnClickListener {
            listener?.OnNextQuestion()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnNextQuestionListener) {
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

    interface OnNextQuestionListener {
        fun OnNextQuestion()
    }

    companion object {
        @JvmStatic
        fun newInstance(chosen : String, right : String, totalCorrect: Int,
                        current: Int, passedData: HashMap<String, String>?) =
                SummaryFragment().apply {
                    arguments = Bundle().apply {
                        putString("chosen", chosen)
                        putString("right", right)
                        putInt("correct", totalCorrect)
                        putInt("current", current)
                    }
                    data = passedData
                }
    }
}
