package com.jammes.boletimnota10.collections.turma

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.jammes.boletimnota10.databinding.FragmentFormTurmaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TurmaFormFragment : Fragment() {

    private var _binding: FragmentFormTurmaBinding? = null
    private val binding get() = _binding!!
    private lateinit var turmaViewModel: TurmaViewModel
    private lateinit var adapter: PeriodoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        turmaViewModel = ViewModelProvider(this)[TurmaViewModel::class.java]
        adapter = PeriodoListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFormTurmaBinding.inflate(inflater, container, false)

        turmaViewModel.stateTurmaOnce().observe(viewLifecycleOwner) {
            bindTurmaUiState(it)
        }

        return binding.root
    }

    private fun bindTurmaUiState(uiStateTurmaItem: TurmaViewModel.UiStateTurma) {
        val turmaItem = uiStateTurmaItem.turmaItem

        binding.turmaTextInputLayout.editText?.setText(turmaItem.nome)
        binding.escolaTextInputLayout.editText?.setText(turmaItem.escola)
        binding.turnoTextInputLayout.editText?.setText(turmaItem.turno)
        binding.anoTextInputLayout.editText?.setText(turmaItem.ano)
        binding.dataInicioTextInputLayout.editText?.setText(turmaItem.dataInicio)
        binding.dataFinalTextInputLayout.editText?.setText(turmaItem.dataFinal)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: TurmaFormFragmentArgs by navArgs()

        if (arguments?.containsKey("turma_id") == true) {
            if (!args.turmaId.isNullOrEmpty())
                turmaViewModel.buscarTurma(args.turmaId!!)
        }

        binding.periodoRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.periodoRecyclerView.adapter = adapter

        binding.novoPeriodoButton.setOnClickListener {

            if (turmaViewModel.turmaAtual().isNullOrEmpty()) {

                turmaViewModel.salvarTurma(
                    binding.turmaTextInputLayout.editText?.text.toString(),
                    binding.escolaTextInputLayout.editText?.text.toString(),
                    binding.turnoTextInputLayout.editText?.text.toString(),
                    binding.anoTextInputLayout.editText?.text.toString(),
                    binding.dataInicioTextInputLayout.editText?.text.toString(),
                    binding.dataFinalTextInputLayout.editText?.text.toString(),
                )

            }

            PeriodoFormFragment(turmaViewModel)
                .show(
                    requireActivity().supportFragmentManager, "PeriodoFormDialog"
                )
        }

        turmaViewModel.statePeriodoOnce().observe(viewLifecycleOwner){uiState ->
            bindUiState(uiState)
        }

        binding.cancelarButton.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.salvarButton.setOnClickListener {

            if (turmaViewModel.turmaAtual().isNullOrEmpty()) {

                Snackbar.make(it, "Adicione um Período antes de finalizar a Turma!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            } else {

                turmaViewModel.alterarTurma(
                    turmaViewModel.turmaAtual()!!,
                    binding.turmaTextInputLayout.editText?.text.toString(),
                    binding.escolaTextInputLayout.editText?.text.toString(),
                    binding.turnoTextInputLayout.editText?.text.toString(),
                    binding.anoTextInputLayout.editText?.text.toString(),
                    binding.dataInicioTextInputLayout.editText?.text.toString(),
                    binding.dataFinalTextInputLayout.editText?.text.toString(),
                )

                findNavController().popBackStack()
            }
        }
    }

    private fun bindUiState(uiState: TurmaViewModel.UiStatePeriodo?) {

        uiState?.periodoItem?.let {periodos ->
            adapter.updatePeriodos(periodos)
        }
    }

    override fun onResume() {
        super.onResume()

        turmaViewModel.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}