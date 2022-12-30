package com.gulehri.whatsappreader.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.gulehri.whatsappreader.R
import com.gulehri.whatsappreader.data.viewmodels.NotificationViewModel
import com.gulehri.whatsappreader.databinding.FragmentHomeBinding
import com.gulehri.whatsappreader.ui.adapters.SaveShowAdapter
import com.gulehri.whatsappreader.utils.Extensions.hide
import com.gulehri.whatsappreader.utils.Extensions.setBarTitle
import com.gulehri.whatsappreader.utils.Extensions.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private var _adapter: SaveShowAdapter? = null

    private val adapter get() = _adapter!!
    private val binding get() = _binding!!

    private val viewModel by viewModels<NotificationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().setBarTitle(getString(R.string.action_home))

        setAdapter()
        bindObserver()

    }

    private fun bindObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.readNotification.collect {

                    if (it.isNotEmpty()) {
                        adapter.submitList(it)
                        binding.animationView.hide()
                        binding.rvSaved.show()
                    } else {
                        binding.animationView.show()
                        binding.rvSaved.hide()
                    }
                }
            }
        }


    }

    private fun setAdapter() {
        _adapter = SaveShowAdapter()
        binding.rvSaved.adapter = adapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}