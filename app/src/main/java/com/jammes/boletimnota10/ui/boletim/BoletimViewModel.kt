package com.jammes.boletimnota10.ui.boletim

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BoletimViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is boletim Fragment"
    }
    val text: LiveData<String> = _text
}