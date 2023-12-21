package com.jammes.boletimnota10.ui.boletim

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jammes.boletimnota10.databinding.FragmentBoletimBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BoletimFragment : Fragment() {

    private var _binding: FragmentBoletimBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val boletimViewModel =
            ViewModelProvider(this).get(BoletimViewModel::class.java)

        _binding = FragmentBoletimBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textGallery
        boletimViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}