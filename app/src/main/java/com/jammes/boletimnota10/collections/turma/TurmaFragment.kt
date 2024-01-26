package com.jammes.boletimnota10.collections.turma

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jammes.boletimnota10.databinding.FragmentTurmaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TurmaFragment(): Fragment() {

    private var _binding: FragmentTurmaBinding? = null
    private val binding get() = _binding!!

    private lateinit var turmaViewModel: TurmaViewModel
    private lateinit var adapter: TurmaListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        turmaViewModel = ViewModelProvider(this)[TurmaViewModel::class.java]
        adapter = TurmaListAdapter()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTurmaBinding.inflate(inflater, container, false)

        turmaViewModel.stateTurmaListOnce().observe(viewLifecycleOwner) { uiState ->
            bindUiState(uiState)
        }

        return binding.root
    }

    private fun bindUiState(uiState: TurmaViewModel.UiStateTurmaList) {

        adapter.updateTurmas(uiState.turmaItemList)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.turmaRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.turmaRecyclerView.adapter = adapter

    }

    override fun onResume() {
        super.onResume()

        turmaViewModel.onResumeTurma()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}