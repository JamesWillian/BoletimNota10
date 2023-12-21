package com.jammes.boletimnota10.ui.professor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jammes.boletimnota10.databinding.FragmentProfessorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfessorFragment: Fragment() {

    private var _binding: FragmentProfessorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfessorBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}