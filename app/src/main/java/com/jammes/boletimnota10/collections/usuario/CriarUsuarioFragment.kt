package com.jammes.boletimnota10.collections.usuario

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.jammes.boletimnota10.R
import com.jammes.boletimnota10.databinding.FragmentCriarUsuarioBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CriarUsuarioFragment: Fragment() {

    private var _binding: FragmentCriarUsuarioBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCriarUsuarioBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.criarUsuarioButton.setOnClickListener {
            viewModel.criarUsuarioAluno(
                binding.emailEditText.text.toString(),
                binding.senhaEditText.text.toString()
            )

            findNavController().popBackStack()
        }

        binding.voltarTextView.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.usuarioFrame, LoginFragment())
                .commitNow()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}