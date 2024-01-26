package com.jammes.boletimnota10.collections.turma

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.jammes.boletimnota10.databinding.FragmentFormTurmaBinding
import com.jammes.boletimnota10.collections.disciplina.DisciplinaViewModel
import com.jammes.boletimnota10.collections.model.ModuloItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TurmaFormFragment : Fragment() {

    private var _binding: FragmentFormTurmaBinding? = null
    private val binding get() = _binding!!

//    private lateinit var disciplinaViewModel: DisciplinaViewModel
    private lateinit var turmaViewModel: TurmaViewModel
    private lateinit var adapter: ModuloListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        disciplinaViewModel = ViewModelProvider(this)[DisciplinaViewModel::class.java]
        turmaViewModel = ViewModelProvider(this)[TurmaViewModel::class.java]
        adapter = ModuloListAdapter(turmaViewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFormTurmaBinding.inflate(inflater, container, false)

        turmaViewModel.stateTurmaItemOnce().observe(viewLifecycleOwner) {
            bindTurmaUiState(it)
        }

//        disciplinaViewModel.stateOnceAndStream().observe(viewLifecycleOwner) {
//            bindUiState(it)
//        }

        return binding.root
    }

    private fun bindTurmaUiState(uiStateTurmaItem: TurmaViewModel.UiStateTurmaItem) {
        val turmaItem = uiStateTurmaItem.turmaItem

        binding.turmaTextInputLayout.editText?.setText(turmaItem.nome)
        binding.escolaTextInputLayout.editText?.setText(turmaItem.escola)
        binding.turnoTextInputLayout.editText?.setText(turmaItem.turno)
        binding.anoTextInputLayout.editText?.setText(turmaItem.ano)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: TurmaFormFragmentArgs by navArgs()

        if (!args.turmaId.isNullOrEmpty())
            turmaViewModel.buscarTurma(args.turmaId!!)

        binding.salvarButton.setOnClickListener {

            turmaViewModel.saveTurma(
                binding.turmaTextInputLayout.editText?.text.toString(),
                binding.escolaTextInputLayout.editText?.text.toString(),
                binding.turnoTextInputLayout.editText?.text.toString(),
                binding.anoTextInputLayout.editText?.text.toString(),
                "", ""
            )

            Snackbar.make(it, "Turma salva com sucesso!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

        }
    }

    override fun onResume() {
        super.onResume()
//        disciplinaViewModel.onResume()
    }

//    private fun bindUiState(uiState: DisciplinaViewModel.UiState) {
//        adapter.updateDisciplinas(
//            uiState.disciplinaItemList.map {
//                ModuloItem(
//                    it.id,
//                    "",
//                    it.descricao
//                )
//            }
//        )
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}