package com.jammes.boletimnota10.collections.turma

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jammes.boletimnota10.collections.model.ModuloItem
import com.jammes.boletimnota10.databinding.ModuloItemBinding

class ModuloListAdapter(
    private val viewModel: TurmaViewModel
): RecyclerView.Adapter<ModuloListAdapter.ViewHolder>() {

    class ViewHolder(
        private val binding: ModuloItemBinding,
        private val viewModel: TurmaViewModel
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(disciplina: ModuloItem) {
            binding.titleTextView.text = disciplina.disciplina

//            binding.checkbox.setOnCheckedChangeListener { _, isChecked ->
//                if (isChecked) viewModel.disciplinasSelecionadas.add(disciplina)
//                else viewModel.disciplinasSelecionadas.remove(disciplina)
//            }
        }
    }

    private val asyncListDiffer: AsyncListDiffer<ModuloItem> = AsyncListDiffer(this, DiffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ModuloItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, viewModel)
    }

    override fun getItemCount(): Int = asyncListDiffer.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(asyncListDiffer.currentList[position])
    }

    fun updateDisciplinas(disciplinas: List<ModuloItem>) {
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