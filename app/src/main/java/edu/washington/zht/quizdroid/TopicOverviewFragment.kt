package edu.washington.zht.quizdroid

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class TopicOverviewFragment : Fragment() {

    private var data: Topic? = null
    private var listener: OnStartQuizListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_topic_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val topicHeading: TextView = view.findViewById(R.id.textViewTopicName)
        topicHeading.text = data!!.title
        val topicDescription: TextView = view.findViewById(R.id.textViewTopicDesc)
        topicDescription.text = data!!.description
        val numQuestions: TextView = view.findViewById(R.id.textViewTopicNumQuestions)
        numQuestions.text = data!!.questions.size.toString() + " Questions"
        val beginBtn: Button = view.findViewById(R.id.buttonTopicStart)
        beginBtn.setOnClickListener {
            listener?.OnStartQuiz()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnStartQuizListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnStartQuizListener {
        fun OnStartQuiz()
    }

    companion object {
        @JvmStatic
        fun newInstance(passedData: Topic?) =
                TopicOverviewFragment().apply {
                    data = passedData
                }
    }
}
