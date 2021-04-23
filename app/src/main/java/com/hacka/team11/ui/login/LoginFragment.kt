package com.hacka.team11.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.hacka.team11.R
import com.hacka.team11.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {


    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        setClickListeners()
        return binding.root
    }

    private fun setClickListeners() {

        binding.layoutLogin.textSignup.setOnClickListener {
            goToSignUp()
        }
        binding.layoutPhone.textSignupPhone.setOnClickListener {
            goToSignUp()
        }


    }


   private fun  goToSignUp(){
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

}