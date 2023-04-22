package com.example.healthylife

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView

class FragmentOne : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_one, container, false)
        val sex = arrayOf("Мужской", "Женский")
        var result: Int

        val arrayAdapter: ArrayAdapter<String>? =
            context?.let { ArrayAdapter(it, android.R.layout.simple_list_item_1, sex) }
        var sexListView: ListView = view.findViewById(R.id.sex_list)
        sexListView.adapter = arrayAdapter
        // Inflate the layout for this fragment

        sexListView.setOnItemClickListener {
            _, _, position, _ ->
            var selectedItem = sex[position]
            result = if (selectedItem == "Мужской") 1 else 0
            Log.d("fragmentOne", "result$result")
        }

        return view
    }

}