package com.example.opscpoe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment

class SettingsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.settings, container, false)

        val unitRadioGroup: RadioGroup = view.findViewById(R.id.unitRadioGroup)
        val distanceSeekBar: SeekBar = view.findViewById(R.id.distanceSeekBar)
        val distanceTextView: TextView = view.findViewById(R.id.distanceTextView)

        // Initialize with km
        distanceTextView.text = "Max Distance: 0 km"

        // Update based on radio button selection
        unitRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            val unit = if (checkedId == R.id.metricRadioButton) "km" else "miles"
            val currentProgress = distanceSeekBar.progress
            distanceTextView.text = "Max Distance: $currentProgress $unit"
        }

        // Update based on seek bar movement
        distanceSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val unit = if (view.findViewById<RadioButton>(R.id.metricRadioButton).isChecked) "km" else "miles"
                distanceTextView.text = "Max Distance: $progress $unit"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        return view
    }
}
