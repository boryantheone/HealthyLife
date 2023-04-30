package com.example.healthylife

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class FragmentSeven : Fragment() {
    private val dataModel: DataModel by activityViewModels()
    private val url = "http://127.0.0.1:5000/predict"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_seven, container, false)

        val testForResult = view.findViewById<TextView>(R.id.substitute_text)
        val params = getParams()
        Log.d("fragments", "${params.toString()}")
        val stringRequest = StringRequest(Request.Method.POST, url,
            {
                try {
                    val jsonObject = JSONObject(params as Map<*, *>)
                    val data = jsonObject.getString("Result_w")
                    testForResult.text = data.toString()
                    Log.d("fragments", "${data}response")
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            {
                Log.d("fragments", "volley error ${it.message}")
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            })

        val queue = Volley.newRequestQueue(context)
        queue.add(stringRequest)
        val button = view.findViewById<Button>(R.id.tryAgain)
        button?.setOnClickListener {
                findNavController().navigate(R.id.action_fragmentSeven_to_startFragment)
        }
        return view
    }

    private fun getParams(): HashMap<String, String> {
        val params = hashMapOf<String, String>()
        params["Gender"] = dataModel.answerOne.value.toString()
        params["Age"] = dataModel.answerTwo.value.toString()
        params["Height"] = dataModel.answerThree.value.toString()
        params["Calories"] = dataModel.answerFive.value.toString()
        params["Weight"] = dataModel.answerFour.value.toString()
        params["Activity"] = dataModel.answerSix.value.toString()
        Log.d("fragments", "weight = ${dataModel.answerFour.value.toString()}")
        Log.d("fragments", "calories = ${dataModel.answerFive.value.toString()}")
        return params
    }
}