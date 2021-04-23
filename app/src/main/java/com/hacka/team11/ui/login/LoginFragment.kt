package com.hacka.team11.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.hacka.team11.MainActivity
import com.hacka.team11.R
import com.hacka.team11.databinding.FragmentLoginBinding
import java.util.*


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
        binding.fbLoginButton.setPermissions(listOf("email"))


        binding.fbLoginButton.registerCallback( (activity as MainActivity).callbackManager,
            object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) {
                    Log.d("RRR", "onSuccess: ")
                    loginResult?.accessToken?.let { handleAccessToken(it) }
                }

                override fun onCancel() {
                    Log.d("RRR", "onCancel: ")
                }

                override fun onError(exception: FacebookException) {
                    Log.d("RRR", "onError: ")
                }
            })


    }


    private fun handleAccessToken(token: AccessToken) {

        val credential = FacebookAuthProvider.getCredential(token.token)
        (activity as MainActivity).firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    val user = (activity as MainActivity).firebaseAuth.currentUser
                    goToMain()
                } else {
                    Log.d("RRR", "handleAccessToken:${task.exception?.message} ")
                }

            }


    }

    private fun goToMain() {
        findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

//        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private fun goToSignUp() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

}