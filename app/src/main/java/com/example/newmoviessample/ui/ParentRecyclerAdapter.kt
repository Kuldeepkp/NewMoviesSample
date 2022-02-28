package com.example.newmoviessample.ui

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newmoviessample.R
import com.example.newmoviessample.models.MoviesEntity

class ParentRecyclerAdapter(private var mData: List<MoviesEntity?>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val height = Resources.getSystem().displayMetrics.heightPixels
    inner class NowPlayingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val mTitleText: TextView = view.findViewById(R.id.typeCategory)
        private val recyclerView: RecyclerView = view.findViewById(R.id.childRecycler)
        private val adapter = NowPlayingAdapter(emptyList())

        fun setData(moviesEntity: MoviesEntity?) {
            mTitleText.text = moviesEntity?.type
            recyclerView.adapter = adapter
            moviesEntity?.nowResults?.let {
                adapter.updateData(it)
            }
        }
    }


    inner class UpComingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val mTitleText: TextView = view.findViewById(R.id.typeCategory)
        private val recyclerView: RecyclerView = view.findViewById(R.id.childRecycler)
        private val adapter = UpComingAdapter(emptyList())

        fun setData(moviesEntity: MoviesEntity?) {
            mTitleText.text = moviesEntity?.type
            recyclerView.adapter = adapter
            moviesEntity?.upComingResults?.let {
                adapter.updateData(it)
            }
        }
    }


    inner class PopularViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val mTitleText: TextView = view.findViewById(R.id.typeCategory)
        private val recyclerView: RecyclerView = view.findViewById(R.id.childRecycler)
        private val adapter = PopularAdapterAdapter(emptyList())

        fun setData(moviesEntity: MoviesEntity?) {
            mTitleText.text = moviesEntity?.type
            recyclerView.adapter = adapter
            moviesEntity?.popularResults?.let {
                adapter.updateData(it)
            }
        }
    }


    inner class TopRatedViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val mTitleText: TextView = view.findViewById(R.id.typeCategory)
        private val recyclerView: RecyclerView = view.findViewById(R.id.childRecycler)
        private val adapter = TopRatedAdapter(emptyList())

        fun setData(moviesEntity: MoviesEntity?) {
            mTitleText.text = moviesEntity?.type
            recyclerView.adapter = adapter
            moviesEntity?.topRatedResults?.let {
                adapter.updateData(it)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        when (viewType) {
            1 -> {
                val holder = NowPlayingViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.parent_recycler_adapter, parent, false)

                )
                holder.itemView.layoutParams.height = height / 4
                holder.itemView.requestLayout()
                return holder
            }
            2 -> {
                val holder = UpComingViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.parent_recycler_adapter, parent, false)
                )
                holder.itemView.layoutParams.height = height / 4
                holder.itemView.requestLayout()
                return holder
            }
            3 -> {
                val holder = PopularViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.parent_recycler_adapter, parent, false)
                )
                holder.itemView.layoutParams.height = height / 4
                holder.itemView.requestLayout()
                return holder
            }
            4 -> {
                val holder = TopRatedViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.parent_recycler_adapter, parent, false)
                )
                holder.itemView.layoutParams.height = height / 4
                holder.itemView.requestLayout()
                return holder
            }
            else -> {
                val holder = NowPlayingViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.parent_recycler_adapter, parent, false)
                )
                holder.itemView.layoutParams.height = height / 4
                holder.itemView.requestLayout()
                return holder
            }
        }

    }


    override fun getItemViewType(position: Int): Int {
        when (mData[position]?.type) {
            "NowPlaying" -> return 1
            "UpComing" -> return 2
            "Popular" -> return 3
            "TopRated" -> return 4
        }
        return super.getItemViewType(position)
    }


    override fun getItemCount(): Int {
        return mData.size
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = mData[position]
        when (holder) {

            is NowPlayingViewHolder -> {
                holder.setData(data)
            }

            is UpComingViewHolder -> {
                holder.setData(data)
            }

            is PopularViewHolder -> {
                holder.setData(data)
            }

            is TopRatedViewHolder -> {
                holder.setData(data)
            }
        }

    }

}