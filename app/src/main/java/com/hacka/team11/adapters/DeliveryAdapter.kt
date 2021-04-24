package com.hacka.team11.adapters


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hacka.team11.BR
import com.hacka.team11.R
import com.hacka.team11.data.local.model.Deliveries
import com.hacka.team11.data.local.model.DeliveryDetail
import com.hacka.team11.databinding.DeliveryitemBinding
import com.hacka.team11.utils.PlayersCredits


class DeliveryAdapter() :
    ListAdapter<Deliveries, DeliveryAdapter.MyMatchViewHolder>(MatchItemDiffCallback()) {



    inner class MyMatchViewHolder(private val binding: DeliveryitemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(deliveries: Deliveries, position: Int) {

            binding.setVariable(BR.delivery, deliveries)
            binding.executePendingBindings()


              val  pos = position


                binding.overName.text = "Ball ${pos}"

                binding.batsmanName.text = checkOverClass(pos, deliveries)?.batsman.toString()
                binding.bowlerName.text = checkOverClass(pos, deliveries)?.bowler.toString()
                binding.batsmanRun.text = checkOverClass(pos, deliveries)?.runs?.batsman.toString()
                binding.extras.text = checkOverClass(pos, deliveries)?.runs?.extras.toString()
                binding.nonStrickerName.text =
                    checkOverClass(pos, deliveries)?.non_striker.toString()
                binding.wicketPlayrOut.text =
                    checkOverClass(pos, deliveries)?.wicket?.player_out.toString()
                binding.wicketFielders.text =
                    checkOverClass(pos, deliveries)?.wicket?.fielders?.getOrNull(0).toString()
                binding.wicketType.text =
                    "Reason for out: ${checkOverClass(pos, deliveries)?.wicket?.kind.toString()}"

                binding.matchCard.setOnClickListener {
                    onItemClickListener?.let { click ->
                        click(position, deliveries)
                    }
                }
            }

    }

    private fun checkOverClass(position: Int, delivery: Deliveries): DeliveryDetail? {

        return when (PlayersCredits.delivery_map.get(position)) {

            "o0_1" -> delivery.o0_1
            "o0_2" -> delivery.o0_2
            "o0_3" -> delivery.o0_3
            "o0_4" -> delivery.o0_4
            "o0_5" -> delivery.o0_5
            "o0_6" -> delivery.o0_6
            "o1_1" -> delivery.o1_1
            "o1_2" -> delivery.o1_2
            "o1_3" -> delivery.o1_3
            "o1_4" -> delivery.o1_3
            "o1_5" -> delivery.o1_5
            "o1_6" -> delivery.o1_6
            "o2_1" -> delivery.o2_1
            "o2_2" -> delivery.o2_2
            "o2_3" -> delivery.o2_3
            "o2_4" -> delivery.o2_4
            "o2_5" -> delivery.o2_4
            "o2_6" -> delivery.o2_4
            "o3_1" -> delivery.o2_4
            "o3_2" -> delivery.o2_4
            "o3_3" -> delivery.o2_4
            "o3_4" -> delivery.o2_4
            "o3_5" -> delivery.o2_4
            "o3_6" -> delivery.o2_4
            "o4_1" -> delivery.o2_4
            "o4_2" -> delivery.o2_4
            "o4_3" -> delivery.o2_4
            "o4_4" -> delivery.o2_4
            "o4_5" -> delivery.o2_4
            "o4_6" -> delivery.o2_4
            "o5_1" -> delivery.o2_4
            "o5_2" -> delivery.o2_4
            "o5_3" -> delivery.o2_4
            "o5_4" -> delivery.o1_1
            "o5_5" -> delivery.o1_1
            "o5_6" -> delivery.o1_1

            "o6_1" -> delivery.o1_1
            "o6_2" -> delivery.o1_1
            "o6_3" -> delivery.o1_1
            "o6_4" -> delivery.o1_1
            "o6_5" -> delivery.o1_1
            "o6_6" -> delivery.o1_1
            "o7_1" -> delivery.o1_1
            "o7_2" -> delivery.o7_2
            "o7_3" -> delivery.o7_3
            "o7_4" -> delivery.o7_4
            "o7_5" -> delivery.o7_5
            "o7_6" -> delivery.o7_6
            "o8_1" -> delivery.o8_1
            "o8_2" -> delivery.o8_2
            "o8_3" -> delivery.o8_3
            "o8_4" -> delivery.o8_4
            "o8_5" -> delivery.o8_5
            "o8_6" -> delivery.o8_6
            "o9_1" -> delivery.o9_1
            "o9_2" -> delivery.o9_2
            "o9_3" -> delivery.o9_3
            "o9_4" -> delivery.o9_4
            "o9_5" -> delivery.o9_5
            "o9_6" -> delivery.o9_6
            "o10_1" -> delivery.o10_1
            "o10_2" -> delivery.o1_2
            "o10_3" -> delivery.o10_3
            "o10_4" -> delivery.o10_4
            "o10_5" -> delivery.o10_5
            "o10_6" -> delivery.o10_6
            "o11_1" -> delivery.o11_1
            "o11_2" -> delivery.o11_2
            "o11_3" -> delivery.o11_3
            "o11_4" -> delivery.o11_4
            "o11_5" -> delivery.o11_5
            "o11_6" -> delivery.o11_6
            "o12_1" -> delivery.o12_1
            "o12_2" -> delivery.o12_2
            "o12_3" -> delivery.o12_3
            "o12_4" -> delivery.o12_4
            "o12_5" -> delivery.o12_5
            "o12_6" -> delivery.o12_6
            "o13_1" -> delivery.o13_1
            "o13_2" -> delivery.o13_2
            "o13_3" -> delivery.o13_3
            "o13_4" -> delivery.o13_4
            "o13_5" -> delivery.o13_5
            "o13_6" -> delivery.o13_6
            "o14_1" -> delivery.o14_1
            "o14_2" -> delivery.o14_2
            "o14_3" -> delivery.o14_3
            "o14_4" -> delivery.o14_4
            "o14_5" -> delivery.o14_5
            "o14_6" -> delivery.o14_6
            "o15_1" -> delivery.o15_1
            "o15_2" -> delivery.o15_2
            "o15_3" -> delivery.o15_3
            "o15_4" -> delivery.o15_4
            "o15_5" -> delivery.o15_5
            "o15_6" -> delivery.o15_6

            "o16_1" -> delivery.o16_1
            "o16_2" -> delivery.o16_2
            "o16_3" -> delivery.o16_3
            "o16_4" -> delivery.o16_4
            "o16_5" -> delivery.o16_5
            "o16_6" -> delivery.o17_1
            "o17_1" -> delivery.o17_1
            "o17_2" -> delivery.o17_2
            "o17_3" -> delivery.o17_3
            "o17_4" -> delivery.o17_4
            "o17_5" -> delivery.o17_5
            "o17_6" -> delivery.o17_6

            "o18_1" -> delivery.o18_1
            "o18_2" -> delivery.o18_2
            "o18_3" -> delivery.o18_3
            "o18_4" -> delivery.o18_4
            "o18_5" -> delivery.o18_5
            "o18_6" -> delivery.o18_6


            "o19_1" -> delivery.o19_1
            "o19_2" -> delivery.o19_2
            "o19_3" -> delivery.o19_3
            "o19_4" -> delivery.o19_4
            "o19_5" -> delivery.o19_5
            "o19_6" -> delivery.o19_6

            else -> {
                return null
            }


        }
    }



    class MatchItemDiffCallback : DiffUtil.ItemCallback<Deliveries>() {
        override fun areItemsTheSame(oldItem: Deliveries, newItem: Deliveries): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Deliveries, newItem: Deliveries): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyMatchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: DeliveryitemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.deliveryitem, parent, false)
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


    private var onItemClickListener: ((position: Int, delivery: Deliveries) -> Unit)? = null
    fun setOnItemClickListener(listener: (position: Int, delivery: Deliveries) -> Unit) {
        onItemClickListener = listener
    }


}