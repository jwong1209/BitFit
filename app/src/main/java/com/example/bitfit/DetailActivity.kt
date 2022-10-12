package com.example.bitfit

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

private const val TAG = "DetailActivity"

class DetailActivity : AppCompatActivity() {
    private lateinit var enterFoodTV: TextView
    private lateinit var enterCaloriesTV: TextView
    private lateinit var enterEntryButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Find the views for the screen
        enterFoodTV = findViewById(R.id.enterFood)
        enterCaloriesTV = findViewById(R.id.enterCalories)
        enterEntryButton = findViewById(R.id.enterEntryButton)

        enterEntryButton.setOnClickListener{

            lifecycleScope.launch (IO) {
                (application as EntryApplication).db.articleDao().insert(EntryEntity(0.toLong(),enterFoodTV.text.toString(), enterCaloriesTV.text.toString()) )
            }
            finish()
        }
    }
}