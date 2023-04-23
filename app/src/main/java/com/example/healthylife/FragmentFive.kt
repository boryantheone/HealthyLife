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

class FragmentFive : Fragment() {
    private val dataModel: DataModel by activityViewModels()
    private var result: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_five, container, false)
        val editTextCalorie = view.findViewById<EditText>(R.id.calorie)
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
        (activity as MainActivity).showKeyboard(editTextCalorie)
        editTextCalorie.setOnEditorActionListener { v, actionId, event ->
            if (editTextCalorie.text.toString().isEmpty() || !editTextCalorie.checkCalories()) {
                val toast = Toast.makeText(
                    activity,
                    "Некорректное значение или слишком опасно для здоровья!",
                    Toast.LENGTH_SHORT
                )
                toast.show()
                editTextCalorie.text.clear()
            }
            result = editTextCalorie.text.toString()
            dataModel.answerFour.value = result
            Log.d("fragments", "result5=$result")
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                (activity as MainActivity).hideKeyboard(editTextCalorie)
                true
            }
            false
        }
        val button = view.findViewById<Button>(R.id.buttonNext5)
        button?.setOnClickListener {
            if (result.isNotEmpty()) {
                findNavController().navigate(R.id.action_fragmentFive_to_fragmentSix)
            } else {
                val toast = Toast.makeText(activity, "Введите значение!", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
        return view
    }

    private fun EditText.checkCalories(): Boolean {
        if (this.text.isDigitsOnly()) {
            return try {
                val text = this.text.toString().toInt()
                text in (1000..10000)
            } catch (e: Exception) {
                false
            }
        }
        return false
    }
}