package com.jammes.boletimnota10.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jammes.boletimnota10.databinding.BoletimItemBinding
import com.jammes.boletimnota10.ui.model.TurmaDisciplinaItem

class BoletimListAdapter(): RecyclerView.Adapter<BoletimListAdapter.ViewHolder>() {

    class ViewHolder(
        private val binding: BoletimItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(disciplina: TurmaDisciplinaItem) {
            binding.disciplinaTextView.text = disciplina.disciplina
            binding.root.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToNavAvaliacoes(disciplina.turmaId,disciplina.id)
                itemView.findNavController().navigate(action)
            }
        }
    }

    private val asyncListDiffer: AsyncListDiffer<TurmaDisciplinaItem> = AsyncListDiffer(this, DiffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = BoletimItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = asyncListDiffer.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(asyncListDiffer.currentList[position])
    }

    fun buscarDisciplinas(disciplinas: List<TurmaDisciplinaItem>) {
        asyncListDiffer.submitList(disciplinas)
    }

    object DiffCallback : DiffUtil.ItemCallback<TurmaDisciplinaItem>() {

        override fun areItemsTheSame(oldItem: TurmaDisciplinaItem, newItem: TurmaDisciplinaItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TurmaDisciplinaItem, newItem: TurmaDisciplinaItem): Boolean {
            return oldItem.disciplina == newItem.disciplina
        }
    }

}