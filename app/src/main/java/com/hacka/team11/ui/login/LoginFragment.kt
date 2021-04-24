package com.hacka.team11.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.alarm.momentix.utils.*
import com.facebook.AccessToken
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.hacka.team11.ui.activity.MainActivity
import com.hacka.team11.R
import com.hacka.team11.databinding.FragmentLoginBinding
import java.util.concurrent.TimeUnit


class LoginFragment : Fragment() {


    private lateinit var binding: FragmentLoginBinding

    private var flag = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        setPhoneViews()
        setClickListeners()
        return binding.root
    }

    private fun setPhoneViews() {
        if (flag) {
            binding.layoutLogin.root.gone()
            binding.layoutPhone.root.visible()
            binding.layoutOtp.root.gone()
            binding.phoneLog.text = "Login with Email"

        } else {
            binding.layoutLogin.root.visible()
            binding.layoutPhone.root.gone()
            binding.layoutOtp.root.gone()
            binding.phoneLog.text = "Login with phone-number"
        }
    }


    private fun setClickListeners() {

        binding.layoutLogin.textSignup.setOnClickListener {
            goToSignUp()
        }
        binding.layoutPhone.textSignupPhone.setOnClickListener {
            goToSignUp()
        }
        binding.fbLoginButton.setPermissions(listOf("email"))
        binding.fbLoginButton.registerCallback((activity as MainActivity).callbackManager,
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


        // login with phone number
        binding.phoneLog.setOnClickListener {

            flag = !flag
            setPhoneViews()

        }
        binding.layoutPhone.cirLoginButton.setOnClickListener {

            if (!binding.layoutPhone.editTextPhone.text.isNullOrBlank()) {
                loginWithPhone(
                    (activity as MainActivity).firebaseAuth,
                    binding.layoutPhone.editTextPhone.text.toString()
                )
                binding.layoutPhone.root.gone()
                binding.layoutOtp.root.visible()
            } else {
                binding.layoutPhone.editTextPhone.error = "Please enter phone number"
                context?.toast("Please enter phone number")
            }


        }


        binding.layoutLogin.cirLoginButton.setOnClickListener {
            if (!binding.layoutLogin.editTextEmail.text.isNullOrBlank() && !binding.layoutLogin.editTextPassword.text.isNullOrBlank()) {
                (activity as MainActivity).firebaseAuth.signInWithEmailAndPassword(
                    binding.layoutLogin.editTextEmail.text.toString(),
                    binding.layoutLogin.editTextPassword.text.toString()
                ).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success

                        goToMain()


                    } else {
                        // Sign in failed
                        context?.toast_long("Authentication failed, Please check your email and password and login again")
                        setPhoneViews()

                    }
                }
            } else {
                context?.toast("Please fill all detials before continuing")
            }

        }


    }

    private fun loginWithPhone(auth: FirebaseAuth, phoneNumber: String) {


        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                signInWithPhoneAuthCredential(auth, credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                if (e is FirebaseAuthInvalidCredentialsException) {
                    context?.toast_long("Invalid request")
                } else if (e is FirebaseTooManyRequestsException) {
                    context?.toast_long("SMS quota for project has been exceeded")
                }
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                super.onCodeSent(verificationId, token)

                Log.d("RRR", "onCodeSent: ${verificationId}  ${token}")
                // Save verification ID and resending token so we can use them later
                val storedVerificationId = verificationId
                val resendToken = token
                binding.layoutOtp.submit2.setOnClickListener {
                    val code = binding.layoutOtp.pinView.text.toString()
                    val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
                    signInWithPhoneAuthCredential(auth, credential)
                }
            }

        }




        auth.useAppLanguage()
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber("+91$phoneNumber")       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity as MainActivity)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)

    }

    private fun signInWithPhoneAuthCredential(auth: FirebaseAuth, credential: PhoneAuthCredential) {


        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                goToMain()
            } else {

                if (task.exception is FirebaseAuthInvalidCredentialsException) {
                    context?.toast_long("The verification code entered is invalid")
                }
            }

        }


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
        if ((activity as MainActivity).firebaseAuth.currentUser != null) {
            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        } else {
            context?.toast("Please login first")
        }

    }


    private fun goToSignUp() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

}