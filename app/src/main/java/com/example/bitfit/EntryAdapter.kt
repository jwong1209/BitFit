package com.example.bitfit

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
import org.w3c.dom.Text

const val ARTICLE_EXTRA = "ARTICLE_EXTRA"
private const val TAG = "ArticleAdapter"

class ArticleAdapter(private val context: Context, private val entries: List<DisplayEntry>) :
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_items, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // TODO: Get the individual article and bind to holder
        val article = entries[position]
        holder.bind(article)
    }

    override fun getItemCount() = entries.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val foodTV = itemView.findViewById<TextView>(R.id.foodTextView)
        private val calorieInputTV = itemView.findViewById<TextView>(R.id.calorieInputTextView)
        private val calorieDisplayTV = itemView.findViewById<TextView>(R.id.caloriesDisplayTextView)

        init {
            itemView.setOnClickListener(this)
        }

        // TODO: Write a helper method to help set up the onBindViewHolder method
        fun bind(entry: DisplayEntry) {
            foodTV.text = entry.food
            calorieInputTV.text = entry.calorie.toString()
            /*
            Glide.with(context)
                .load(article.mediaImageUrl)
                .into(foodTV)*/
        }

        override fun onClick(v: View?) {
            //Do Nothing
        }
    }
}