package com.jammes.boletimnota10.ui.turma

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.jammes.boletimnota10.databinding.FragmentTurmaBinding
import com.jammes.boletimnota10.ui.disciplina.DisciplinaListAdapter
import com.jammes.boletimnota10.ui.disciplina.DisciplinaViewModel
import com.jammes.boletimnota10.ui.model.TurmaDisciplinaItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TurmaFragment : Fragment() {

    private var _binding: FragmentTurmaBinding? = null
    private val binding get() = _binding!!

    private lateinit var disciplinaViewModel: DisciplinaViewModel
    private lateinit var turmaViewModel: TurmaViewModel
    private lateinit var adapter: TurmaDisciplinaListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        disciplinaViewModel = ViewModelProvider(this)[DisciplinaViewModel::class.java]
        turmaViewModel = ViewModelProvider(this)[TurmaViewModel::class.java]
        adapter = TurmaDisciplinaListAdapter(turmaViewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTurmaBinding.inflate(inflater, container, false)

        disciplinaViewModel.stateOnceAndStream().observe(viewLifecycleOwner) {
            bindUiState(it)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.disciplinaRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.disciplinaRecyclerView.adapter = adapter

        binding.salvarButton.setOnClickListener {

            turmaViewModel.saveTurma(
                binding.turmaTextInputLayout.editText?.text.toString(),
                binding.escolaTextInputLayout.editText?.text.toString(),
                binding.periodoTextInputLayout.editText?.text.toString(),
                binding.turnoTextInputLayout.editText?.text.toString(),
                binding.anoTextInputLayout.editText?.text.toString().toInt(),
            )

            Snackbar.make(it, "Turma salva com sucesso!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

        }
    }

    override fun onResume() {
        super.onResume()
        disciplinaViewModel.onResume()
    }

    private fun bindUiState(uiState: DisciplinaViewModel.UiState) {
        adapter.updateDisciplinas(
            uiState.disciplinaItemList.map {
                TurmaDisciplinaItem(
                    it.id,
                    it.descricao
                )
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}