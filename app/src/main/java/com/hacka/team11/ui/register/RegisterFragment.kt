package com.hacka.team11.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.alarm.momentix.utils.toast_long
import com.hacka.team11.R
import com.hacka.team11.databinding.FragmentRegisterBinding
import com.hacka.team11.ui.activity.MainActivity


class RegisterFragment : Fragment() {




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

        binding.layoutRegister.cirSignUpButton.setOnClickListener {

            if(binding.layoutRegister.editTextEmail.text.isNullOrBlank()){
                binding.layoutRegister.editTextEmail.error = "Please enter email"
            }
            else if (binding.layoutRegister.editTextUsername.text.isNullOrBlank()){
                binding.layoutRegister.editTextUsername.error = "Please enter uername"
            }
            else if (binding.layoutRegister.editTextPassword.text.isNullOrBlank()){
                binding.layoutRegister.editTextPassword.error = "Please enter password"
            }
            else if (binding.layoutRegister.editTextMobile.text.isNullOrBlank()){
                binding.layoutRegister.editTextMobile.error = "Please enter mobile number"
            }
            else{

                (activity as MainActivity).firebaseAuth.createUserWithEmailAndPassword( binding.layoutRegister.editTextEmail.text.toString(), binding.layoutRegister.editTextPassword.text.toString())
                    .addOnCompleteListener {task->

                        if(task.isSuccessful){
                            goToMain()
                        }
                        else{
                            context?.toast_long("Authentication failed")
                        }

                    }


            }




        }

    }

    private fun goToMain() {
        if((activity as MainActivity).firebaseAuth.currentUser!=null){
            findNavController().navigate(R.id.action_registerFragment_to_mainFragment)
        }
    }

    private fun goToLogin() {
        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
    }


}