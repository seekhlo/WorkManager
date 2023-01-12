package com.example.workmanager

import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    var number = 0;
    fun addNumber() {

        number++
    }
}
