package com.jammes.boletimnota10.collections.usuario

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jammes.boletimnota10.R
import com.jammes.boletimnota10.databinding.FragmentUsuarioBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsuarioFragment : Fragment() {

    private var _binding: FragmentUsuarioBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsuarioBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            requireActivity().supportFragmentManager.beginTransaction()
                .add(R.id.usuarioFrame, LoginFragment())
                .commitNow()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}