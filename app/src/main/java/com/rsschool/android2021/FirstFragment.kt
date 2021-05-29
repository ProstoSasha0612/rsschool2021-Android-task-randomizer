package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private lateinit var editTextMin: EditText
    private lateinit var editTextMax: EditText
    private lateinit var fragmentSendMinMax: OnFragmentSendMinMax

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentSendMinMax = context as OnFragmentSendMinMax
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)
        editTextMin = view.findViewById(R.id.min_value)
        editTextMax = view.findViewById(R.id.max_value)


        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        generateButton?.setOnClickListener {
            val min: Int = if(editTextMin.text.isNotEmpty()) editTextMin.text.toString().toInt() else 0
            val max: Int = if(editTextMax.text.isNotEmpty()) editTextMax.text.toString().toInt() else 0

            fragmentSendMinMax.sendMinMax(min,max)
        }

        val textWatcher = object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(count < 1) generateButton?.let { it.isEnabled = false }
            }

            override fun afterTextChanged(s: Editable?) {
                val min = if(editTextMin.text.isNotEmpty()) editTextMin.text.toString().toInt() else 0
                val max = if(editTextMax.text.isNotEmpty()) editTextMax.text.toString().toInt() else 0
                if(min>max){
                    generateButton?.let { it.isEnabled = false }
                }else{
                    generateButton?.let { it.isEnabled = true }
                }
            }
        }

        editTextMin.addTextChangedListener(textWatcher)
        editTextMax.addTextChangedListener(textWatcher)
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}