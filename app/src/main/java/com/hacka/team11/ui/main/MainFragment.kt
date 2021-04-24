package com.hacka.team11.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.hacka.team11.R
import com.hacka.team11.adapters.MatchAdapter
import com.hacka.team11.databinding.FragmentMainBinding
import com.hacka.team11.ui.activity.MainActivity
import com.hacka.team11.utils.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment() {


    @Inject
    lateinit var matchAdapter: MatchAdapter

    private lateinit var binding: FragmentMainBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        setViews()


        binding.toolbar.setOnMenuItemClickListener {
            onOptionsItemSelected(it)
        }
        setHasOptionsMenu(true)
        setSlidingBehaviour()
        setClickListeners()

        return binding.root
    }

    private fun setClickListeners() {

        matchAdapter.setOnItemClickListener {position,match->

            val bundle = Bundle().apply {
                putSerializable(Constants.MATCH_OBJ, match)
            }

            findNavController().navigate(R.id.action_mainFragment_to_matchDetailsFragment,bundle)
        }

    }

    private fun setViews() {
        binding.fragmentListamatchRecylerView.apply {
            adapter = matchAdapter
        }
        matchAdapter.submitList((activity as MainActivity).match_list)

    }


    override fun onStart() {
        super.onStart()
        (activity as MainActivity).window.setNavigationBarColor(getResources().getColor(R.color.white))
    }


    private fun setSlidingBehaviour() {
        val behavior = BottomSheetBehavior.from(binding.bottomSheet)
        if (checkAboveKitkat()) {

            behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                        binding.bottomSheet.setPadding(0, 0, 0, 0)

                    } else if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                        activity?.getStatusBarHeight()?.let { bottomSheet.setPadding(0, it, 0, 0) }

                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {


                    bottomSheet.setPadding(
                        0,
                        (slideOffset * activity?.getStatusBarHeight()!!).toInt(),
                        0,
                        0
                    )
                }
            })
        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_setting -> {
                findNavController().navigate(R.id.action_mainFragment_to_settingsFragment)
                return true
            }
            R.id.action_rate -> {

                activity?.rateUs()
                return true
            }
            R.id.action_share -> {
                activity?.share("Playstore link will be inserted here ", "text")
                return true
            }
            R.id.action_logout->{
                (activity as MainActivity).firebaseAuth.signOut()
                findNavController().navigate(R.id.action_mainFragment_to_loginFragment)
                return true
            }
            else -> {
                return false
            }
        }
    }

}