package com.example.mynewsapp.activity.main.pages

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mynewsapp.R
import com.example.mynewsapp.model.News

class NewsListAdapter(
    private val activity: Activity,
    private val fragment:Fragment,
    private val itemLayoutResource: Int,
    private val onItemClick: ((Int) -> Unit)
) : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    private val itemList: ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(activity).inflate(itemLayoutResource, parent, false))
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = itemList[position]

        holder.titleTextView.text = news.title

        Glide
            .with(fragment)
            .load(news.urlToImage)
            .centerInside()
            .into(holder.imageView)
    }

    fun setItems(newItemList: ArrayList<News>) {
        itemList.clear()
        itemList.addAll(newItemList)
        notifyDataSetChanged()
    }

    fun getItem(position: Int): News {
        return itemList[position]
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val titleTextView: TextView = itemView.findViewById(R.id.textViewTitle)

        init {
            itemView.setOnClickListener {
                onItemClick.invoke(adapterPosition)
            }
        }
    }

}