package com.jammes.boletimnota10.ui.aluno

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jammes.boletimnota10.databinding.FragmentAlunoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlunoFragment: Fragment() {

    private var _binding: FragmentAlunoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAlunoBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}