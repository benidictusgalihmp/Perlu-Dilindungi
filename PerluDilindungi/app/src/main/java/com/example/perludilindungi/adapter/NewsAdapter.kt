package com.example.perludilindungi.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.perludilindungi.databinding.NewsCardLayoutBinding
import com.example.perludilindungi.model.News
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

//    private var image: Bitmap? = null
    private var newsList = emptyList<News>()
//    private var clickListener: ItemClickListener? = null

    class NewsViewHolder(val binding: NewsCardLayoutBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(NewsCardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.binding.newsTitle.text = newsList[position].title
        holder.binding.newsDate.text = newsList[position].pubDate
        Glide.with(holder.itemView.context).load(newsList[position].enclosure._url).into(holder.binding.newsEnclosure)
//        var image = getImage(newsList[position].enclosure._url)
//        holder.binding.newsEnclosure.setImageBitmap(image)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun setNewsData(newNewsList: List<News>) {
//        clickListener = onItemClick
        newsList = newNewsList
        notifyDataSetChanged()
    }

//    interface ItemClickListener {
//        fun onItemClick(news: News)
//    }

//    suspend fun getImage(url: String): Bitmap? = coroutineScope {
//        var image: Bitmap? = null
//        launch {
//            val `img` = java.net.URL(url).openStream()
//            image = BitmapFactory.decodeStream(`img`)
//        }
//        return@coroutineScope image
//    }

}