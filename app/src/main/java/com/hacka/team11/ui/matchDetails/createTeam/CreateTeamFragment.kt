package com.hacka.team11.ui.matchDetails.createTeam

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hacka.team11.adapters.PlayerAdapter
import com.hacka.team11.data.local.model.MatchModel
import com.hacka.team11.data.local.model.Players
import com.hacka.team11.databinding.FragmentCreateTeamBinding
import com.hacka.team11.utils.Constants
import com.hacka.team11.utils.PlayersCredits
import com.hacka.team11.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@AndroidEntryPoint
class CreateTeamFragment : Fragment() {
    private var match: MatchModel? = null

    private lateinit var binding: FragmentCreateTeamBinding

    @Inject
    lateinit var playerAdapter: PlayerAdapter


    private val player_list = ArrayList<Players>()
    private val selected_players = ArrayList<Players>()

    private var creditremaining: Double = 100.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            match = it.getSerializable(Constants.MATCH_OBJ) as MatchModel

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateTeamBinding.inflate(inflater, container, false)


        setData()
        setClickListeners()

        return binding.root
    }

    private fun setData() {


        binding.fragmentListaplayersRecylerView.apply {
            adapter = playerAdapter
        }

        playerAdapter.submitList(player_list)

        val generator = Random()
        val start = generator.nextInt(288)
        val end = start + 23

        for (i in start..end) {
            player_list.add(
                Players(
                    PlayersCredits.player_cretdit_map.keys.elementAt(i),
                    PlayersCredits.player_cretdit_map.values.elementAt(i)
                )
            )
        }



        playerAdapter.setOnItemCheckBoxClickListener { position, player, isChecked, checkBox, card ->

            if (isChecked) {
                if (selected_players.size < 11) {
                    selected_players.add(player)

                    card.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#DDDDDD"))
                    binding.progressBar.progress =
                        binding.progressBar.progress + (player.credit).toInt()

                    if (creditremaining - player.credit > 0) {
                        creditremaining -= player.credit

                        checkBox.isEnabled = true

                    } else {

                        checkBox.isEnabled = false


                        context?.toast("All credits used. You cannot select more players. ")
                    }



                    binding.creditRemaining.text = (creditremaining).toString()
                } else {
                    context?.toast("You can only select 11 players")
                }

            } else {
                if (selected_players.contains(player)) {
                    card.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#ffffff"))
                    selected_players.remove(player)
                    binding.progressBar.progress =
                        binding.progressBar.progress - (player.credit).toInt()

                    if (creditremaining + player.credit <= 100) {
                        creditremaining += player.credit
                    } else {
                        //pass
                    }


                    binding.creditRemaining.text = (creditremaining).toString()
                }
            }
            if (selected_players.size == 11) {
                binding.chooseTeam.isEnabled = true
            }
        }


    }

    private fun setClickListeners() {

    }


}