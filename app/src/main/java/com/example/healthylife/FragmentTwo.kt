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

class FragmentTwo : Fragment() {
    private val dataModel: DataModel by activityViewModels()
    private var result: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_two, container, false)
        val editTextAge = view.findViewById<EditText>(R.id.age)
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
        (activity as MainActivity).showKeyboard(editTextAge)
//        showKeyboard(editTextAge)
        editTextAge.setOnEditorActionListener { v, actionId, event ->
            if (editTextAge.text.toString().isEmpty() || !editTextAge.checkAge())
            {
                val toast = Toast.makeText(activity, "Некорректное значение!", Toast.LENGTH_SHORT)
                toast.show()
                editTextAge.text.clear()
            }
            result = editTextAge.text.toString()
            dataModel.answerTwo.value = result
            Log.d("fragments", "result1=$result")
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                (activity as MainActivity).hideKeyboard(editTextAge)
                true
            }
            false
        }
        val button = view.findViewById<Button>(R.id.buttonNext2)
        button?.setOnClickListener {
            if (result.isNotEmpty()) {
                findNavController().navigate(R.id.action_fragmentTwo_to_fragmentThree)
            } else {
                val toast = Toast.makeText(activity, "Введите значение!", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
        return view
    }

    private fun EditText.checkAge() : Boolean {
        if (this.text.isDigitsOnly()) {
            return try {
                val text = this.text.toString().toInt()
                text in (10..120)
            } catch (e: Exception) {
                false
            }
        }
        return false
    }
}