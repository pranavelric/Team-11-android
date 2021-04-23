package com.hacka.team11.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.hacka.team11.R
import com.hacka.team11.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
    }

    lateinit var binding: FragmentRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)

        setClickListners()
        return binding.root
    }

    private fun setClickListners() {
        binding.layoutRegister.loginText.setOnClickListener {
            goToLogin()
        }
    }

    private fun goToLogin() {
        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
    }


}