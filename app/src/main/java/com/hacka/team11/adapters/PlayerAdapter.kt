package com.hacka.team11.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.hacka.team11.BR
import com.hacka.team11.R
import com.hacka.team11.data.local.model.Players
import com.hacka.team11.databinding.ItemPlayerBinding


class PlayerAdapter() :
    ListAdapter<Players, PlayerAdapter.MyMatchViewHolder>(MatchItemDiffCallback()) {

    inner class MyMatchViewHolder(private val binding: ItemPlayerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(player: Players, position: Int) {

            binding.setVariable(BR.player, player)
            binding.executePendingBindings()



            binding.playerCard.setOnClickListener {
                onItemClickListener?.let { click ->
                    click(position, player)
                }
            }

            binding.checkboxPlayer.setOnCheckedChangeListener { buttonView, isChecked ->
                onItemCheckBoxClickListener?.let { click ->
                    click(position, player, isChecked, binding.checkboxPlayer, binding.playerCard)
                }

            }

        }
    }


    class MatchItemDiffCallback : DiffUtil.ItemCallback<Players>() {
        override fun areItemsTheSame(oldItem: Players, newItem: Players): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Players, newItem: Players): Boolean {
            return oldItem.name == newItem.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyMatchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemPlayerBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_player, parent, false)
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


    private var onItemClickListener: ((position: Int, player: Players) -> Unit)? = null
    fun setOnItemClickListener(listener: (position: Int, player: Players) -> Unit) {
        onItemClickListener = listener
    }

    private var onItemCheckBoxClickListener: ((position: Int, player: Players, checked: Boolean, cb: CheckBox, card: MaterialCardView) -> Unit)? =
        null

    fun setOnItemCheckBoxClickListener(listener: (position: Int, player: Players, checked: Boolean, cb: CheckBox, card: MaterialCardView) -> Unit) {
        onItemCheckBoxClickListener = listener
    }


}