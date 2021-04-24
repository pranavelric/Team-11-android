package com.hacka.team11.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hacka.team11.BR
import com.hacka.team11.R
import com.hacka.team11.data.local.model.MatchModel
import com.hacka.team11.databinding.MatchitemBinding
import com.hacka.team11.utils.getForamttedTimeFromStrgin

class MatchAdapter() :
    ListAdapter<MatchModel, MatchAdapter.MyMatchViewHolder>(MatchItemDiffCallback()) {

    inner class MyMatchViewHolder(private val binding: MatchitemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(match: MatchModel, position: Int) {

            binding.setVariable(BR.match, match)
            binding.executePendingBindings()

            binding.time.text = getForamttedTimeFromStrgin(match.info.dates.getOrElse(0,){
                ""
            })

            binding.matchCard.setOnClickListener {
                onItemClickListener?.let { click ->
                    click(position,match)
                }
            }

        }
    }


    class MatchItemDiffCallback : DiffUtil.ItemCallback<MatchModel>() {
        override fun areItemsTheSame(oldItem: MatchModel, newItem: MatchModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MatchModel, newItem: MatchModel): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyMatchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: MatchitemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.matchitem, parent, false)
        return MyMatchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyMatchViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }


    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    private var onItemClickListener: ((position: Int, match:MatchModel) -> Unit)? = null
    fun setOnItemClickListener(listener: (position: Int,match:MatchModel) -> Unit) {
        onItemClickListener = listener
    }


}