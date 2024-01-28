package com.jammes.boletimnota10.collections.turma

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.jammes.boletimnota10.databinding.FragmentFormPeriodoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeriodoFormFragment(
    private val viewModel: TurmaViewModel,
): DialogFragment() {

    private var _binding: FragmentFormPeriodoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFormPeriodoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.salvarButton.setOnClickListener {

            val periodo = binding.periodoTextInputLayout.editText?.text.toString().trim()

            if (periodo.isEmpty()) {
                Toast.makeText(requireContext(),"Informe o Período",Toast.LENGTH_SHORT).show()
            } else {
                viewModel.salvarPeriodo(periodo)
                dismiss()
            }
        }

        binding.cancelarButton.setOnClickListener {
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}