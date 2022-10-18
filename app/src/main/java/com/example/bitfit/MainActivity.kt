package com.example.bitfit

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bitfit.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    val calorieList = ArrayList<Int>()

    private val articles = mutableListOf<DisplayEntry>()
    private lateinit var articlesRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding
    private lateinit var enterDetailButton: Button
    private lateinit var resetButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root*/
        setContentView(R.layout.activity_main)
        /*
        enterDetailButton = findViewById(R.id.enterDetailButton)
        resetButton = findViewById(R.id.resetButton)
        articlesRecyclerView = findViewById(R.id.entries)
        // TODO: Set up ArticleAdapter with articles
        val articleAdapter = ArticleAdapter(this, articles)
        articlesRecyclerView.adapter = articleAdapter

        articlesRecyclerView.layoutManager = LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            articlesRecyclerView.addItemDecoration(dividerItemDecoration)
        }

        lifecycleScope.launch {
            (application as EntryApplication).db.articleDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    DisplayEntry(
                        entity.food,
                        entity.calorie
                    )
                }.also { mappedList ->
                    articles.clear()
                    articles.addAll(mappedList)
                    articleAdapter.notifyDataSetChanged()
                }
            }
        }

        enterDetailButton.setOnClickListener{

            val intent = Intent(view.context, DetailActivity::class.java)
            view.context?.startActivity(intent)
            articleAdapter.notifyDataSetChanged()
        }

        resetButton.setOnClickListener {
            lifecycleScope.launch (IO) {
                (application as EntryApplication).db.articleDao().deleteAll()
            }
        }*/
        val fragmentManager: FragmentManager = supportFragmentManager

        // define your fragments here
        val listFragment: Fragment = ListFragment()
        val overviewFragment: Fragment = OverviewFragment()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // handle navigation selection
        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.nav_list -> fragment = listFragment
                R.id.nav_overview -> fragment = overviewFragment
            }
            replaceFragment(fragment)
            true
        }

        // Set default selection
        bottomNavigationView.selectedItemId = R.id.nav_list
    }

    private fun replaceFragment(xFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.list_frame_layout, xFragment)
        fragmentTransaction.commit()
    }
}