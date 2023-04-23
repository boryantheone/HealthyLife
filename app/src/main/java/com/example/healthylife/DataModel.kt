package com.example.healthylife

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class DataModel: ViewModel() {
    val answerOne: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val answerTwo: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val answerThree: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val answerFour: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val answerFive: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val answerSix: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}