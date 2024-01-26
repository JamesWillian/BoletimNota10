package com.jammes.boletimnota10.collections.avaliacao

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.jammes.boletimnota10.databinding.FragmentAvaliacaoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AvaliacaoFragment: Fragment() {

    private var _binding: FragmentAvaliacaoBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: AvaliacaoViewModel

    private lateinit var adapter: AvaliacaoListAdapter

    private lateinit var turmaId: String
    private lateinit var disciplinaId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[AvaliacaoViewModel::class.java]
        adapter = AvaliacaoListAdapter(viewModel, requireActivity().supportFragmentManager)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAvaliacaoBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: AvaliacaoFragmentArgs by navArgs()
        turmaId = args.turmaId
        disciplinaId = args.disciplinaId

        binding.avaliacoesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.avaliacoesRecyclerView.adapter = adapter

        binding.novaAvaliacaoButton.setOnClickListener {
            AvaliacaoFormFragment(
                viewModel,
                turmaId,
                disciplinaId)
                .show(
                    requireActivity().supportFragmentManager, "AvaliacaoFormDialog"
                )
        }

        viewModel.stateOnceAndStream().observe(viewLifecycleOwner) {
            bindUiState(it)
        }
    }

    private fun bindUiState(uiState: AvaliacaoViewModel.UiState) {
        adapter.recarregarAvaliacoes(uiState.avaliacaoItemList)
    }

    override fun onResume() {
        super.onResume()

        viewModel.onResume(turmaId, disciplinaId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}