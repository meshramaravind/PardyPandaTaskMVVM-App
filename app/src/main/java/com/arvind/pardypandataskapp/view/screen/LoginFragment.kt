package com.arvind.pardypandataskapp.view.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.arvind.pardypandataskapp.R
import com.arvind.pardypandataskapp.databinding.FragmentLoginBinding
import com.arvind.pardypandataskapp.view.base.BaseFragment
import com.arvind.pardypandataskapp.viewmodel.LoginViewModel
import com.arvind.pardypandataskapp.viewmodel.PhotoViewModel
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    override val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        doInitis()
    }

    private fun doInitis() = with(binding) {
        generateOtpButton.setOnClickListener {
            val phoneNumber = binding.phoneNumberEditText.text.toString()
            if (phoneNumber.isNotEmpty()) {
                viewModel.sendVerificationCode(phoneNumber)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Please enter a phone number...",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }

        }

        verifyOtpButton.setOnClickListener {

            val code = binding.verificationCodeEditText.text.toString()
            if (code.isNotEmpty()) {
                viewModel.verifyCode(code)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Please enter the your otp...",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }

        viewModel.isLoading.observe(requireActivity(), Observer { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })

        viewModel.authResult.observe(requireActivity(), Observer { result ->
            result?.let {
                if (it) {
                    Toast.makeText(
                        requireContext(),
                        "Authentication successful...",
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().navigate(R.id.action_loginFragment_to_photoFragment)
                } else {
                    Toast.makeText(requireContext(), "Authentication failed...", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })

    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)
}