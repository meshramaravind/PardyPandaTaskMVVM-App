package com.arvind.pardypandataskapp.view.screen

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.arvind.pardypandataskapp.adapter.PhotoAdapter
import com.arvind.pardypandataskapp.databinding.FragmentPhotoBinding
import com.arvind.pardypandataskapp.view.base.BaseFragment
import com.arvind.pardypandataskapp.viewmodel.PhotoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoFragment : BaseFragment<FragmentPhotoBinding, PhotoViewModel>() {
    override val viewModel: PhotoViewModel by viewModels()
    private lateinit var adapter: PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        doInitis()
    }

    private fun doInitis() = with(binding) {
        rvPhotos.layoutManager = LinearLayoutManager(requireContext())

        viewModel.photos.observe(viewLifecycleOwner, Observer { photos ->
            if (photos.isNullOrEmpty()) {
                progessBar.visibility = View.VISIBLE
                rvPhotos.visibility = View.GONE
            } else {
                progessBar.visibility = View.GONE
                rvPhotos.visibility = View.VISIBLE
                adapter = PhotoAdapter(photos)
                rvPhotos.adapter = adapter
                rvPhotos.setHasFixedSize(true)
            }
        })
        progessBar.visibility = View.VISIBLE
        fetchPhotos()
    }

    private fun fetchPhotos() {
        if (isNetworkAvailable()) {
            binding.progessBar.visibility = View.VISIBLE
            viewModel.fetchPhotos()
        } else {
            Toast.makeText(
                requireContext(),
                "No internet connection. Loading offline data...",
                Toast.LENGTH_LONG
            ).show()
            viewModel.fetchPhotos()
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnected == true
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentPhotoBinding.inflate(inflater, container, false)

}