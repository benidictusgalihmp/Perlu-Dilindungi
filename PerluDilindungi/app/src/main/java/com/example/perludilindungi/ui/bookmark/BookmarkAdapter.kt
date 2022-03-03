package com.example.perludilindungi.ui.bookmark

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.perludilindungi.R
import com.example.perludilindungi.entities.BookmarkDisplayMini
import kotlinx.android.synthetic.main.bookmark_item.view.*

class BookmarkAdapter(
    private var bookmarkList: List<BookmarkDisplayMini> = emptyList<BookmarkDisplayMini>()
): RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder>() {

    class BookmarkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        return BookmarkViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.bookmark_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val currentItem = bookmarkList[position]
        holder.itemView.apply {
            tvNama.text = currentItem.nama
            tvTelp.text = currentItem.alamat
        }
    }

    override fun getItemCount(): Int {
        return bookmarkList.size
    }

    fun setData(bookmarks: List<BookmarkDisplayMini>){
        this.bookmarkList = bookmarks
        notifyDataSetChanged()
    }
}