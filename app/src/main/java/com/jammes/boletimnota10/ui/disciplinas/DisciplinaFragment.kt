package com.jammes.boletimnota10.ui.disciplinas

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jammes.boletimnota10.core.database.AppDatabase
import com.jammes.boletimnota10.core.repository.DisciplinaRepositoryImpl
import com.jammes.boletimnota10.databinding.FragmentDisciplinaBinding
import com.jammes.boletimnota10.ui.domain.GetAllDisciplinasUseCaseImpl
import com.jammes.boletimnota10.ui.domain.InsertDisciplinaUseCaseImpl

class DisciplinaFragment : Fragment() {

    private var _binding: FragmentDisciplinaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adapter: DisciplinaListAdapter

    private val disciplinaViewModel: DisciplinaViewModel by viewModels {
        val db = AppDatabase.getInstance(requireContext())
        val disciplinaRepository = DisciplinaRepositoryImpl(db)
        DisciplinaViewModel.Factory(
            getAllDisciplinasUseCase = GetAllDisciplinasUseCaseImpl(disciplinaRepository),
            insertDisciplinaUseCase = InsertDisciplinaUseCaseImpl(disciplinaRepository)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = DisciplinaListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDisciplinaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        disciplinaViewModel.stateOnceAndStream().observe(viewLifecycleOwner) {
            bindUiState(it)
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.disciplinaRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.disciplinaRecyclerView.adapter = adapter

        binding.saveButton.isEnabled = false
        binding.disciplinaTextInputLayout.editText?.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {

                    binding.saveButton.isEnabled = !(s.isNullOrEmpty())

                }

            }

        )

        binding.saveButton.setOnClickListener {
            onSave()
            binding.disciplinaTextInputLayout.editText?.text?.clear()
        }
    }

    private fun onSave() {
        val disciplina = binding.disciplinaTextInputLayout.editText?.text.toString().trim()
        disciplinaViewModel.saveDisciplina(disciplina)
    }

    override fun onResume() {
        super.onResume()
        disciplinaViewModel.onResume()
    }

    private fun bindUiState(uiState: DisciplinaViewModel.UiState) {
        adapter.updateDisciplinas(uiState.disciplinaItemList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}