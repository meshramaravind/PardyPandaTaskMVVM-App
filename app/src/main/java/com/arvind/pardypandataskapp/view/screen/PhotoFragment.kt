package com.arvind.pardypandataskapp.view.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
            adapter = PhotoAdapter(photos)
            rvPhotos.adapter = adapter
        })

    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentPhotoBinding.inflate(inflater, container, false)

}