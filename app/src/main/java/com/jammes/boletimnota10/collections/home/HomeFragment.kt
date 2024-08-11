package com.jammes.boletimnota10.collections.home

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.jammes.boletimnota10.R
import com.jammes.boletimnota10.core.repository.EncryptedSharedPreferencesUtil
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

//        addingDividerDecoration()

        homeViewModel.stateTurmaUiState().observe(viewLifecycleOwner) {uiState ->
            bindUiStateTurma(uiState)
        }
        homeViewModel.statePeriodoUiState().observe(viewLifecycleOwner) {uiState ->
            bindUiStatePeriodo(uiState)
        }
        homeViewModel.stateBoletimUiStateOnce().observe(viewLifecycleOwner) {uiState ->
            bindUiStateBoletim(uiState)
        }

//        homeViewModel.existeTurma().observe(viewLifecycleOwner) {existeTurma ->
//            if (!existeTurma)
//                findNavController().navigate(R.id.nav_form_turma)
//        }

        binding.editarTurmaButton.setOnClickListener {
            val action = HomeFragmentDirections.actionNavHomeToNavFormTurma(homeViewModel.turmaAtual())
            findNavController().navigate(action)
//            val token = EncryptedSharedPreferencesUtil.getSessionToken(requireContext())
//            if (token == null)
//                Toast.makeText(requireContext(), "Sem Token", Toast.LENGTH_SHORT).show()
//            else
//                Toast.makeText(requireContext(), token, Toast.LENGTH_SHORT).show()
        }

    }

    private fun bindUiStatePeriodo(uiState: HomeViewModel.PeriodoUiState) {
        val periodos = uiState.periodoItem
        val chipGroup = binding.chipGroup

        chipGroup.removeAllViews()

        for (chipPeriodo in periodos) {
            val chip = Chip(requireContext())

            chip.text = chipPeriodo.periodo
            chip.isChecked = false
            chip.isCheckable = true
            chip.chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.chip_background))

            chip.setOnClickListener {
                homeViewModel.listarBoletimDoPeriodo(chipPeriodo.id)
            }

            chipGroup.addView(chip)
            chipGroup.check(chipGroup[0].id)
        }
    }

    private fun bindUiStateTurma(uiState: HomeViewModel.TurmaUiState) {
        val turma = uiState.turmaItem

        if (turma.id.isEmpty()) {//Chama a tela de cadastro de Turma, se não existir turma cadastrada para o aluno
            findNavController().navigate(R.id.nav_form_turma)
            return
        } else {
            binding.turmaTextView.text = turma.nome
            binding.escolaTextView.text = turma.escola
            binding.turnoTextView.text = turma.turno
            binding.anoTextView.text = turma.ano
            if (turma.concluido)
                binding.concluidoTextView.visibility = View.GONE
        }
    }

    private fun bindUiStateBoletim(uiState: HomeViewModel.BoletimUiState) {
        adapter.buscarBoletim(uiState.boletimItem)
    }

    private fun addingDividerDecoration() {
        val divider = MaterialDividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        val resources = requireContext().resources

        divider.isLastItemDecorated = false
        divider.dividerThickness = resources.getDimensionPixelSize(R.dimen.vertical_margin)
        divider.dividerColor = ContextCompat.getColor(requireContext(), R.color.soft_blue)

        binding.disciplinasRecyclerView.addItemDecoration(divider)
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