package com.jammes.boletimnota10.collections.usuario

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.jammes.boletimnota10.R
import com.jammes.boletimnota10.collections.disciplina.DisciplinaFragment
import com.jammes.boletimnota10.core.repository.EncryptedSharedPreferencesUtil
import com.jammes.boletimnota10.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.visitanteButton.setOnClickListener {
            val token = EncryptedSharedPreferencesUtil.getSessionToken(requireContext())
            if (token == null)
                viewModel.criarUsuarioAnonimo()

            findNavController().popBackStack()
        }

        binding.loginButton.setOnClickListener {
            val username = binding.usuarioEditText.text.toString()
            val password = binding.senhaEditText.text.toString()

            viewModel.login(username, password)

            findNavController().popBackStack()
        }

        binding.criarContaTextView.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.usuarioFrame, CriarUsuarioFragment())
                .commitNow()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}