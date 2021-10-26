package by.brust.task_5_cats.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.brust.task_5_cats.data.Cat
import by.brust.task_5_cats.databinding.ItemCatBinding
import coil.load

class CatAdapter(private val listener: OnImageClickListener) : PagingDataAdapter<Cat, CatAdapter.CatViewHolder>(Diff) {

    companion object {
        private val Diff = object : DiffUtil.ItemCallback<Cat>() {
            override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
                return oldItem == newItem
            }
        }
    }
    inner class CatViewHolder(private val binding: ItemCatBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cat: Cat) {
            binding.apply {
                imageView.load(cat.url)
            }
        }

        init {
            itemView.setOnClickListener {
                if (bindingAdapterPosition != -1) {
                    getItem(bindingAdapterPosition)?.let {
                        listener.onItemClick(it)
                    }
                }
            }
        }
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val cat = getItem(position)
        if (cat != null) {
            holder.bind(cat)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder = CatViewHolder(
        ItemCatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    interface OnImageClickListener {
        fun onItemClick(image: Cat)
    }
}
