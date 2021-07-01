package com.example.countdown

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.countdown.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.seconds().observe(this, Observer {
            binding.tvNumber.text = it.toString()
        })

        viewModel.finished.observe(this, Observer {
            if (it) {
                Toast.makeText(this, "Finished", Toast.LENGTH_SHORT).show()
            }
        })

        binding.btnStart.setOnClickListener {
            if (binding.etNumber.text.isEmpty()) {
                Toast.makeText(this, "Invalid Number", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.timerValue.value = binding.etNumber.text.toString().toLong()
                viewModel.startTimer()
            }
        }

        binding.btnStop.setOnClickListener {
            binding.tvNumber.text = "0"
            viewModel.stopTimer()
        }
    }
}