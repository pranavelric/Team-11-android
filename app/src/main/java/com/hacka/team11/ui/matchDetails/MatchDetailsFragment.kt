package com.hacka.team11.ui.matchDetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.hacka.team11.R
import com.hacka.team11.data.local.model.MatchModel
import com.hacka.team11.databinding.FragmentMatchDetailsBinding
import com.hacka.team11.utils.Constants

class MatchDetailsFragment : Fragment() {
    private var match: MatchModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            match = it.getSerializable(Constants.MATCH_OBJ) as MatchModel?
        }

    }

    private lateinit var binding: FragmentMatchDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMatchDetailsBinding.inflate(inflater, container, false)


        setData()
        setClickListeners()

        return binding.root
    }

    private fun setClickListeners() {

        binding.createTeam.setOnClickListener {

            val bundle = Bundle().apply {
                putSerializable(Constants.MATCH_OBJ, match)
            }
//            findNavController().navigate()

        }

    }

    @SuppressLint("SetTextI18n")
    private fun setData() {
        binding.textViewMatchType.text = match?.info?.match_type + " " + match?.info?.competition
        binding.textViewMatchcity.text = "Location: ${match?.info?.city}"
        binding.textViewteamA.text = match?.info?.teams?.getOrElse(0) {
            "No team found"
        }
        binding.textViewteamB.text = match?.info?.teams?.getOrElse(1) {
            ""
        }
        binding.umpire1.text = "• "+match?.info?.umpires?.getOrElse(0) {
            "No Umpire found"
        }
        binding.umpire2.text ="• "+ match?.info?.umpires?.getOrElse(1) {
            ""
        }
        binding.textViewVenue.text = match?.info?.venue



    }


}