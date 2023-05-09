package com.example.healthylife

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController

class FragmentSix : Fragment() {
    private val dataModel: DataModel by activityViewModels()
    private var result: String = ""
    lateinit var radioGroup: RadioGroup

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_six, container, false)

        radioGroup = view.findViewById(R.id.radioGroup)

        val button = view.findViewById<Button>(R.id.buttonNext6)
        button?.setOnClickListener {
            checkButton(view)
            if (result.isNotEmpty()) {
                findNavController().navigate(R.id.action_fragmentSix_to_fragmentSeven)
            } else {
                val toast = Toast.makeText(activity, "Выберите значение!", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
        return view
    }

    fun checkButton(view: View) {
        val radioId = radioGroup.checkedRadioButtonId

        result = when (radioId) {
            R.id.radioButtonOne -> "1.2"
            R.id.radioButtonTwo -> "1.4"
            R.id.radioButtonThree -> "1.6"
            R.id.radioButtonFour -> "1.7"
            else -> ""
        }
        if (result.isNotEmpty()) {
            dataModel.answerSix.value = result
        }
        Log.d("fragments", "result6=$result")
    }
}