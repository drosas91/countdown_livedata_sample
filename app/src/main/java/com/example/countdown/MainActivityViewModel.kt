package com.example.countdown

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    private lateinit var timer: CountDownTimer
    private val _seconds = MutableLiveData<Int>()
    var timerValue = MutableLiveData<Long>()
    var  finished = MutableLiveData<Boolean>()

    fun seconds(): LiveData<Int> {
        return _seconds
    }

    fun startTimer() {
        timer = object : CountDownTimer(timerValue.value!!.toLong(), 1000) {
            override fun onFinish() {
                finished.value = true
            }

            override fun onTick(p0: Long) {
                val timeLeft = p0/1000
                _seconds.value = timeLeft.toInt()
            }
        }.start()
    }

    fun stopTimer() {
        timer.cancel()
    }
}