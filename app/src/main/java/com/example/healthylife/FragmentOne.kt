package com.example.healthylife

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController

class FragmentOne : Fragment() {
    private val dataModel: DataModel by activityViewModels()
    private var result: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_one, container, false)
        val sex = arrayOf("Мужской", "Женский")


        val arrayAdapter: ArrayAdapter<String>? =
            context?.let { ArrayAdapter(it, android.R.layout.simple_list_item_1, sex) }
        val sexListView: ListView = view.findViewById(R.id.sex_list)
        sexListView.adapter = arrayAdapter
        // Inflate the layout for this fragment

        sexListView.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = sex[position]
            result = if (selectedItem == "Мужской") "0" else "1"
            dataModel.answerOne.value = result
            Log.d("fragments", "result1=$result")
        }

        val button = view.findViewById<Button>(R.id.buttonNext1)
        button?.setOnClickListener {
            if (result.isNotBlank()) {
                findNavController().navigate(R.id.action_fragmentOne_to_fragmentTwo)
            } else {
                val toast = Toast.makeText(activity, "Выберите вариант!", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
        return view
    }
}