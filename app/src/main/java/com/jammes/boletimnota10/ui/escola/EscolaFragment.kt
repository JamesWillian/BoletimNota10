package com.jammes.boletimnota10.ui.escola

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jammes.boletimnota10.databinding.FragmentEscolaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EscolaFragment: Fragment() {

    private var _binding: FragmentEscolaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEscolaBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}