package com.example.healthylife

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController

class FragmentFour : Fragment() {
    private val dataModel: DataModel by activityViewModels()
    private var result: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_four, container, false)
        val editTextWeight = view.findViewById<EditText>(R.id.weight)
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
        (activity as MainActivity).showKeyboard(editTextWeight)
//        showKeyboard(editTextAge)
        editTextWeight.setOnEditorActionListener { v, actionId, event ->
            if (editTextWeight.text.toString().isEmpty() || !editTextWeight.checkWeight())
            {
                val toast = Toast.makeText(activity, "Некорректное значение!", Toast.LENGTH_SHORT)
                toast.show()
                editTextWeight.text.clear()
            }
            result = editTextWeight.text.toString()
            dataModel.answerFour.value = result
            Log.d("fragments", "result4=$result")
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                (activity as MainActivity).hideKeyboard(editTextWeight)
                true
            }
            false
        }
        val button = view.findViewById<Button>(R.id.buttonNext4)
        button?.setOnClickListener {
            if (result.isNotEmpty()) {
                findNavController().navigate(R.id.action_fragmentFour_to_fragmentFive)
            } else {
                val toast = Toast.makeText(activity, "Введите значение!", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
        return view
    }

    private fun EditText.checkWeight() : Boolean {
        if (this.text.isDigitsOnly()) {
            return try {
                val text = this.text.toString().toInt()
                text in (30..350)
            } catch (e: Exception) {
                false
            }
        }
        return false
    }
}