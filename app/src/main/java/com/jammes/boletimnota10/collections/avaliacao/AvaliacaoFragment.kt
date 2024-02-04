package com.jammes.boletimnota10.collections.avaliacao

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.jammes.boletimnota10.R
import com.jammes.boletimnota10.databinding.FragmentAvaliacaoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AvaliacaoFragment: Fragment() {

    private var _binding: FragmentAvaliacaoBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: AvaliacaoViewModel

    private lateinit var adapter: AvaliacaoListAdapter

    private lateinit var moduloId: String

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
        moduloId = args.moduloId

        binding.avaliacoesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.avaliacoesRecyclerView.adapter = adapter

        addingDividerDecoration()

        binding.novaAvaliacaoButton.setOnClickListener {
            AvaliacaoFormFragment(
                viewModel,
                moduloId
            ).show(
                requireActivity().supportFragmentManager, "AvaliacaoFormDialog"
            )
        }

        viewModel.stateOnceAndStream().observe(viewLifecycleOwner) {
            bindUiState(it)
        }
    }

    private fun bindUiState(uiState: AvaliacaoViewModel.UiState) {
        val boletimItem = uiState.boletimItem

        binding.moduloTextView.text = boletimItem?.modulo ?: ""
        binding.professorTextView.text = boletimItem?.professor ?: ""

        adapter.recarregarAvaliacoes(uiState.avaliacaoItemList)
    }

    private fun addingDividerDecoration() {
        val divider = MaterialDividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        val resources = requireContext().resources

        divider.isLastItemDecorated = false
        divider.dividerThickness = resources.getDimensionPixelSize(R.dimen.vertical_margin)
        divider.dividerColor = ContextCompat.getColor(requireContext(), R.color.soft_blue)

        binding.avaliacoesRecyclerView.addItemDecoration(divider)
    }

    override fun onResume() {
        super.onResume()

        viewModel.onResume(moduloId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}