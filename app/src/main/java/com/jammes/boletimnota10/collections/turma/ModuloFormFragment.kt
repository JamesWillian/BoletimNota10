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
import com.jammes.boletimnota10.databinding.FragmentFormModuloBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ModuloFormFragment(
): Fragment() {

    private var _binding: FragmentFormModuloBinding? = null
    private val binding get() = _binding!!
    private lateinit var turmaViewModel: TurmaViewModel
    private lateinit var adapter: ModuloListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        turmaViewModel = ViewModelProvider(requireActivity())[TurmaViewModel::class.java]
        adapter = ModuloListAdapter(turmaViewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFormModuloBinding.inflate(inflater,container,false)

        turmaViewModel.stateModuloOnce().observe(viewLifecycleOwner) {uiState ->
            adapter.updateModulos(uiState.moduloItem)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: ModuloFormFragmentArgs by navArgs()

        binding.modulosRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.modulosRecyclerView.adapter = adapter

        binding.periodoTextView.text = args.periodo
        binding.concluirButton.setOnClickListener {
            findNavController().popBackStack()
        }

        turmaViewModel.onResumeModulos(args.periodoId)
    }

    override fun onResume() {
        super.onResume()

//        viewModel.onResumeModulos()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}