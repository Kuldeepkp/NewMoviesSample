package com.example.newmoviessample.ui

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newmoviessample.R
import com.example.newmoviessample.models.Result

class PopularAdapterAdapter(private var data: List<Result>) :

    RecyclerView.Adapter<PopularAdapterAdapter.ViewHolder>() {
    private val width = Resources.getSystem().displayMetrics.widthPixels

    inner class ViewHolder(private val item_view: View) : RecyclerView.ViewHolder(item_view) {

        fun setData(result: Result) {
            item_view.findViewById<TextView>(R.id.itemTitle).text = result.title
            Glide.with(item_view.context)
                .load("https://image.tmdb.org/t/p/original/${result.poster_path}")
                .into(item_view.findViewById(R.id.itemImage))
        }

    }

    fun updateData(list: List<Result>) {
        data = list
        val count = data.size
        notifyItemRangeChanged(0,count)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularAdapterAdapter.ViewHolder {
        val holder = ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_recycler_adapter, parent, false)
        )
        holder.itemView.layoutParams.width = width / 3
        holder.itemView.requestLayout()
        return holder
    }

    override fun onBindViewHolder(holder: PopularAdapterAdapter.ViewHolder, position: Int) {
        holder.setData(data[position])

    }


    override fun getItemCount(): Int {
        return data.size
    }
}