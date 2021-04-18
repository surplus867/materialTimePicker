package com.example.android.testapp

import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.testapp.databinding.FragmentFirstBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.sql.Time

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        binding.button.setOnClickListener {
            openTimePicker()
        }

        return binding.root
    }

    private fun openTimePicker() {
        val isSystem24Hour = is24HourFormat(requireContext())
        val clockFormat = if(isSystem24Hour) TimeFormat.CLOCK_24H else TimeFormat.CLOCK_12H

        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(clockFormat)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Set Alarm")
            .build()
        picker.show(childFragmentManager, "TAG")

        picker.addOnPositiveButtonClickListener {
            Log.d("FirstFragment", "POSITIVE")
            val h = picker.hour
            val min = picker.minute
            Log.d("FirstFragment", "$h:$min")
        }

        picker.addOnNegativeButtonClickListener {
            Log.d("FirstFragment", "NEGATIVE")
        }

        picker.addOnCancelListener {
            Log.d("FirstFragment", "CANCEL")
        }

        picker.addOnDismissListener {
            Log.d("FirstFragment", "DISMISS")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}