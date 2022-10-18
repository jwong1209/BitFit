package com.example.bitfit

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers.IO

private const val TAG = "OverviewFragment"

class OverviewFragment : Fragment() {
    private lateinit var averageDisplay: TextView
    private lateinit var maxDisplay: TextView
    private lateinit var minDisplay: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_overview, container, false)
        averageDisplay = view.findViewById(R.id.averageDisplay)
        maxDisplay = view.findViewById(R.id.maxDisplay)
        minDisplay = view.findViewById(R.id.minDisplay)

        // Inflate the layout for this fragment
        return view
        //return inflater.inflate(R.layout.fragment_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("WE ARE HERE")
        var avg = ""
        var max = ""
        var min = ""
        lifecycleScope.launch (IO) {
            avg= (requireActivity().application as EntryApplication).db.articleDao().getAvg().toString()
            max = (requireActivity().application as EntryApplication).db.articleDao().getMax().toString()
            min = (requireActivity().application as EntryApplication).db.articleDao().getMin().toString()
            averageDisplay.text = avg.toString()
            minDisplay.text = min.toString()
            maxDisplay.text = max.toString()
        }
    }

    companion object {
        fun newInstance(): OverviewFragment {
            return OverviewFragment()
        }
    }
}