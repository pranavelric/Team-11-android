package com.hacka.team11.ui.matchDetails.liveMatch

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hacka.team11.R
import com.hacka.team11.adapters.DeliveryAdapter
import com.hacka.team11.data.local.model.Deliveries
import com.hacka.team11.data.local.model.MatchModel
import com.hacka.team11.data.local.model.Players
import com.hacka.team11.databinding.FragmentLiveMatchBinding
import com.hacka.team11.utils.Constants
import com.hacka.team11.utils.CoroutinesHelper
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception
import javax.inject.Inject


@AndroidEntryPoint
class LiveMatchFragment : Fragment() {

    private var match: MatchModel? = null
    private var selectedPlayers: ArrayList<Players>? = null

    @Inject
    lateinit var deliveryAdapter1: DeliveryAdapter
    @Inject
    lateinit var deliveryAdapter2: DeliveryAdapter


    private var innings1list = ArrayList<Deliveries>()
    private var innings2list = ArrayList<Deliveries>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            match = it.getSerializable(Constants.MATCH_OBJ) as MatchModel
            selectedPlayers = it.getSerializable(Constants.SELCTED_PLAYERS) as ArrayList<Players>?
        }
    }

    private lateinit var binding: FragmentLiveMatchBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLiveMatchBinding.inflate(layoutInflater, container, false)

        setData()
        setClickListeners()
        return binding.root
    }

    private fun setClickListeners() {

    }

    private fun setData() {

        binding.winnerName.text = match?.info?.outcome?.winner
        if (match?.info?.outcome?.winner == match?.info?.teams?.get(0)) {
            binding.looserName.text = match?.info?.teams?.get(1)
        } else {
            binding.looserName.text = match?.info?.teams?.get(0)
        }
        if (match?.info?.player_of_match?.isNotEmpty() == true)
            for (i in match?.info?.player_of_match!!) {
                binding.playerOfMatch.append("• $i\n")
            }

        binding.resultBy.text =
            match?.info?.outcome?.winner + " Won by " + match?.info?.outcome?.by_team?.runs.toString() + " runs."


        binding.matchInfo.apply{
            adapter = deliveryAdapter1
        }
        binding.matchInfo2.apply {
            adapter = deliveryAdapter2
        }




        if(!match?.innings?.get(0)?.firstInnings?.deliveries.isNullOrEmpty()){

            for(i in 0 until match?.innings?.get(0)?.firstInnings?.deliveries?.size!!){

                match?.innings?.get(0)?.firstInnings?.deliveries?.getOrNull(i)?.let {


                    innings1list.add(
                        it
                    )
                }

            }
        }


        if(!match?.innings?.get(1)?.secondInnings?.deliveries.isNullOrEmpty()){

            for(i in 0 until match?.innings?.get(1)?.secondInnings?.deliveries?.size!!){

                match?.innings?.get(1)?.secondInnings?.deliveries?.getOrNull(i)?.let {
                    Log.d("RRR", "setData: ${it}")
                    innings2list.add(
                        it
                    )
                }

            }
        }

        deliveryAdapter1.submitList(innings1list)
        deliveryAdapter2.submitList(innings2list)




    }


}