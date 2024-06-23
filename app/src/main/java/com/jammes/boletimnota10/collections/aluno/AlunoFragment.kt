package com.jammes.boletimnota10.collections.aluno

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.jammes.boletimnota10.R
import com.jammes.boletimnota10.core.repository.EncryptedSharedPreferencesUtil
import com.jammes.boletimnota10.databinding.FragmentAlunoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlunoFragment: Fragment() {

    private var _binding: FragmentAlunoBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AlunoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[AlunoViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAlunoBinding.inflate(inflater, container, false)

        viewModel.stateOnceAndStream().observe(viewLifecycleOwner){uiState ->
            bindUiState(uiState)
        }

        return binding.root
    }

    private fun bindUiState(uiState: AlunoViewModel.UiState) {
        binding.nomeEditText.setText(uiState.alunoItem.nome)
        binding.salvarNomeButton.visibility = View.GONE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nomeEditText.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrEmpty()) {
                    binding.salvarNomeButton.visibility = View.GONE
                } else {
                    binding.salvarNomeButton.visibility = View.VISIBLE
                }

            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.sairButton.setOnClickListener {
//            EncryptedSharedPreferencesUtil.clearSessionToken(requireContext())
            findNavController().navigate(R.id.usuarioFragment)
        }

//        binding.editarTurmaImageButton.setOnClickListener {
//            if (binding.turmasCardView.height == 60) {
//                binding.turmasCardView.layoutParams.height = 160
//            } else {
//                binding.turmasCardView.layoutParams.height = 60
//            }
//        }

        binding.salvarNomeButton.setOnClickListener {
            viewModel.saveAluno(
                binding.nomeEditText.text.toString()
            )
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}