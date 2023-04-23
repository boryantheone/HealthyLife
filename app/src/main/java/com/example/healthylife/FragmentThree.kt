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

class FragmentThree : Fragment() {
    private val dataModel: DataModel by activityViewModels()
    private var result: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_three, container, false)
        val editTextHeight = view.findViewById<EditText>(R.id.height)
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
        (activity as MainActivity).showKeyboard(editTextHeight)
//        showKeyboard(editTextAge)
        editTextHeight.setOnEditorActionListener { v, actionId, event ->
            if (editTextHeight.text.toString().isEmpty() || !editTextHeight.checkHeight())
            {
                val toast = Toast.makeText(activity, "Некорректное значение!", Toast.LENGTH_SHORT)
                toast.show()
                editTextHeight.text.clear()
            }
            result = editTextHeight.text.toString()
            dataModel.answerThree.value = result
            Log.d("fragments", "result3=$result")
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                (activity as MainActivity).hideKeyboard(editTextHeight)
                true
            }
            false
        }
        val button = view.findViewById<Button>(R.id.buttonNext3)
        button?.setOnClickListener {
            if (result.isNotEmpty()) {
                findNavController().navigate(R.id.action_fragmentThree_to_fragmentFour)
            } else {
                val toast = Toast.makeText(activity, "Введите значение!", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
        return view
    }

    private fun EditText.checkHeight() : Boolean {
        if (this.text.isDigitsOnly()) {
            return try {
                val text = this.text.toString().toInt()
                text in (50..300)
            } catch (e: Exception) {
                false
            }
        }
        return false
    }
}