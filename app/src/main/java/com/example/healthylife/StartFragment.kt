package com.example.healthylife

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController

class StartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_start, container, false)
        val button = view.findViewById<Button>(R.id.do_forecast)
        context?.let { context ->
            button?.setOnClickListener {
                if ((activity as MainActivity).isOnline(context)) {
                    findNavController().navigate(R.id.action_startFragment_to_fragmentOne)
                } else {
                    val toast = Toast.makeText(
                        activity,
                        "Отсутствует подключение к интернету.",
                        Toast.LENGTH_LONG
                    )
                    toast.show()
                }
            }
        }

        // Inflate the layout for this fragment
        return view
    }
}