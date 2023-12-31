package com.jammes.boletimnota10.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jammes.boletimnota10.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private lateinit var adapter: BoletimListAdapter
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        adapter = BoletimListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.disciplinasRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.disciplinasRecyclerView.adapter = adapter

        homeViewModel.stateTurmaUiState().observe(viewLifecycleOwner) {uiState ->
            bindUiStateTurma(uiState)
        }
        homeViewModel.stateBoletimUiStateOnce().observe(viewLifecycleOwner) {uiState ->
            bindUiStateBoletim(uiState)
        }
    }

    private fun bindUiStateTurma(uiState: HomeViewModel.TurmaUiState) {
        val turma = uiState.turmaItem

        binding.turmaTextView.text = turma.nome
        binding.escolaTextView.text = turma.escola
        binding.periodoTextView.text = turma.periodo
        binding.turnoTextView.text = turma.turno
        binding.anoTextView.text = turma.ano.toString()
        if (turma.concluido)
            binding.concluidoTextView.visibility = View.GONE
    }

    private fun bindUiStateBoletim(uiState: HomeViewModel.BoletimUiState) {
        adapter.buscarDisciplinas(uiState.boletimItem)
    }

    override fun onResume() {
        super.onResume()

        homeViewModel.listarBoletim()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}