package com.mia.legacy.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {


    init {
        val channel = Channel<Int>()
        viewModelScope.launch {
            println("=HomeViewModel=aaa")
            var value = 1;
            for (i in 1..10) {
                channel.send(i)
            }
        }

        viewModelScope.launch {
            println("=HomeViewModel=" + channel.receive())
        }
        println("=HomeViewModel=initend")
    }
}