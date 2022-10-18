package com.example.bitfit

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.app.Application


import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.bitfit.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers.IO

private const val TAG = "ListFragment"

class ListFragment : Fragment() {

    private val articles = mutableListOf<DisplayEntry>()
    private lateinit var entryRecyclerView: RecyclerView
    private lateinit var entryAdapter: ArticleAdapter
    private lateinit var enterDetailButton: Button
    private lateinit var resetButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Change this statement to store the view in a variable instead of a return statement
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        // Add these configurations for the recyclerView and to configure the adapter
        val layoutManager = LinearLayoutManager(context)
        entryRecyclerView = view.findViewById(R.id.list_recycler_view)
        entryRecyclerView.layoutManager = layoutManager
        entryRecyclerView.setHasFixedSize(true)
        entryAdapter = ArticleAdapter(view.context, articles)
        entryRecyclerView.adapter = entryAdapter
        resetButton = requireActivity().findViewById(R.id.resetButton)
        enterDetailButton = requireActivity().findViewById(R.id.enterDetailButton)
        entryRecyclerView.layoutManager = LinearLayoutManager(requireActivity()).also {
            val dividerItemDecoration = DividerItemDecoration(requireActivity(), it.orientation)
            entryRecyclerView.addItemDecoration(dividerItemDecoration)
        }

        // Update the return statement to return the inflated view from above
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            (requireActivity().application as EntryApplication).db.articleDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    DisplayEntry(
                        entity.food,
                        entity.calorie
                    )
                }.also { mappedList ->
                    articles.clear()
                    articles.addAll(mappedList)
                    entryAdapter.notifyDataSetChanged()
                }
            }
        }

        enterDetailButton.setOnClickListener{

            val intent = Intent(view.context, DetailActivity::class.java)
            view.context?.startActivity(intent)
            entryAdapter.notifyDataSetChanged()
        }

        resetButton.setOnClickListener {
            lifecycleScope.launch (Dispatchers.IO) {
                (requireActivity().application as EntryApplication).db.articleDao().deleteAll()
            }
        }

    }

    companion object {
        fun newInstance(): ListFragment {
            return ListFragment()
        }
    }
}