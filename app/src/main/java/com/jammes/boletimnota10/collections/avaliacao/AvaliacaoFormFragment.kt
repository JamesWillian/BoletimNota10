package com.jammes.boletimnota10.collections.avaliacao

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.jammes.boletimnota10.collections.model.AvaliacaoItem
import com.jammes.boletimnota10.databinding.FragmentFormAvaliacaoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AvaliacaoFormFragment(
    private val viewModel: AvaliacaoViewModel,
    private val avaliacaoItem: AvaliacaoItem?,
): DialogFragment() {

    private var _binding: FragmentFormAvaliacaoBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFormAvaliacaoBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindUI(avaliacaoItem)

        binding.salvarButton.setOnClickListener {
            viewModel.salvarAvaliacao(
                avaliacaoItem?.id ?: "",
                avaliacaoItem?.moduloId ?: viewModel.moduloAtual(),
                binding.avaliacaoTextInputLayout.editText?.text.toString(),
                binding.notaTextInputLayout.editText?.text.toString().toFloat(),
                binding.dataTextInputLayout.editText?.text.toString(),
                false
            )
            Toast.makeText(requireContext(),"Avaliação salva",Toast.LENGTH_SHORT).show()
            dismiss()
        }

        binding.cancelarButton.setOnClickListener {
            dismiss()
        }
    }

    private fun bindUI(avaliacaoItem: AvaliacaoItem?) {

        if (avaliacaoItem != null) {
            binding.avaliacaoTextInputLayout.editText?.setText(avaliacaoItem.descricao)
            binding.dataTextInputLayout.editText?.setText(avaliacaoItem.data)
            binding.notaTextInputLayout.editText?.setText(avaliacaoItem.nota)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}