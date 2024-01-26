package com.jammes.boletimnota10.collections.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jammes.boletimnota10.databinding.BoletimItemBinding
import com.jammes.boletimnota10.collections.model.ModuloItem

class BoletimListAdapter(): RecyclerView.Adapter<BoletimListAdapter.ViewHolder>() {

    class ViewHolder(
        private val binding: BoletimItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(disciplina: ModuloItem) {
            binding.disciplinaTextView.text = disciplina.disciplina
            binding.root.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToNavAvaliacoes(disciplina.periodoId,disciplina.id)
                itemView.findNavController().navigate(action)
            }
        }
    }

    private val asyncListDiffer: AsyncListDiffer<ModuloItem> = AsyncListDiffer(this, DiffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = BoletimItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = asyncListDiffer.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(asyncListDiffer.currentList[position])
    }

    fun buscarDisciplinas(disciplinas: List<ModuloItem>) {
        asyncListDiffer.submitList(disciplinas)
    }

    object DiffCallback : DiffUtil.ItemCallback<ModuloItem>() {

        override fun areItemsTheSame(oldItem: ModuloItem, newItem: ModuloItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ModuloItem, newItem: ModuloItem): Boolean {
            return oldItem.disciplina == newItem.disciplina
        }
    }

}