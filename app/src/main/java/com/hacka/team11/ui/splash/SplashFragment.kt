package com.hacka.team11.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.hacka.team11.utils.setFullScreen
import com.hacka.team11.utils.setFullScreenForNotch
import com.hacka.team11.ui.activity.MainActivity
import com.hacka.team11.R
import com.hacka.team11.databinding.FragmentSplashBinding
import com.hacka.team11.utils.CoroutinesHelper


class SplashFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSplashBinding =
            FragmentSplashBinding.inflate(inflater, container, false)
        checkUserLogined()
        return binding.root
    }

    private fun checkUserLogined() {

        if ((activity as MainActivity).firebaseAuth.currentUser != null) {
            CoroutinesHelper.delayWithMain(2000L) {
                findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
            }
        } else {
            CoroutinesHelper.delayWithMain(2000L) {
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            }
        }

    }

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).setFullScreen()
        (activity as MainActivity).setFullScreenForNotch()
    }


}