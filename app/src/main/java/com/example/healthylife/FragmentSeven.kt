package com.example.healthylife

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import kotlin.math.roundToInt

class FragmentSeven : Fragment() {
    private val dataModel: DataModel by activityViewModels()
    private val url = "http://healthapp.pythonanywhere.com/predict"   //192.168.1.4

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_seven, container, false)
        val shareButton = view.findViewById<Button>(R.id.shareButton)
        val testForResult = view.findViewById<TextView>(R.id.substitute_text)
        val queue = Volley.newRequestQueue(context)
        val sr: StringRequest = object : StringRequest(Method.POST, url,
            Response.Listener { response ->
                try {
                    val jsonObject = JSONObject(response)
                    val data = jsonObject.getString("Result_w")
                    Log.d("fragments", "${data}response")
                    val result = ((data.toDouble() * 100.0).roundToInt() / 100.0)
                    testForResult.text = result.toString()
                    shareButton.visibility = View.VISIBLE
                    shareButton.setOnClickListener {
                        val intent = Intent(Intent.ACTION_SEND)
                        intent.type = "text/plain"
                        val body = "При соблюдении нормы ${dataModel.answerFive.value.toString()} ккал в день через месяц я буду весить $result"
                        intent.putExtra(Intent.EXTRA_TEXT, body)
                        startActivity(Intent.createChooser(intent, "Share using"))
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { it ->
                if (it.message.isNullOrEmpty()) {
                    Log.d("fragments", "volley error null")
                } else {
                    Log.d("fragments", "volley error ${it.message}")
                }
                Toast.makeText(context, "Проверьте работу интернета.", Toast.LENGTH_LONG).show()
            }) {
            override fun getParams(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["Gender"] = dataModel.answerOne.value.toString()
                params["Age"] = dataModel.answerTwo.value.toString()
                params["Height"] = dataModel.answerThree.value.toString()
                params["Calories"] = dataModel.answerFive.value.toString()
                params["Weight"] = dataModel.answerFour.value.toString()
                params["Activity"] = dataModel.answerSix.value.toString()
                return params
            }
        }
        queue.add(sr)
        val button = view.findViewById<Button>(R.id.tryAgain)
        button?.setOnClickListener {
                findNavController().navigate(R.id.action_fragmentSeven_to_startFragment)
        }
        return view
    }
}