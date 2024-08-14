package com.arvind.pardypandataskapp.view.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.arvind.pardypandataskapp.databinding.FragmentLoginBinding
import com.arvind.pardypandataskapp.databinding.FragmentOtpBinding
import com.arvind.pardypandataskapp.view.base.BaseFragment
import com.arvind.pardypandataskapp.viewmodel.PhotoViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class OtpFragment : BaseFragment<FragmentOtpBinding, PhotoViewModel>() {
    override val viewModel: PhotoViewModel by viewModels()

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doInitis()
    }

    private fun doInitis() = with(binding) {
        login.setOnClickListener {
            val otp = etOtp.text.trim().toString()
            if (otp.isNotEmpty()) {
//                val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(
//                    storedVerificationId.toString(), otp
//                )
//                signInWithPhoneAuthCredential(credential)
            } else {
                Toast.makeText(requireContext(), "Enter OTP", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
//                    val intent = Intent(this , MainActivity::class.java)
//                    startActivity(intent)
//                    finish()
                } else {
                    // Sign in failed, display a message and update the UI
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        Toast.makeText(requireContext(), "Invalid OTP", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()

    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentOtpBinding.inflate(inflater, container, false)

}