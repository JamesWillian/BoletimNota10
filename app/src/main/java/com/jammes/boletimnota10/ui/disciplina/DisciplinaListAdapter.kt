package com.jammes.boletimnota10.ui.disciplina

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jammes.boletimnota10.databinding.DisciplinaItemBinding
import com.jammes.boletimnota10.ui.model.DisciplinaItem

class DisciplinaListAdapter(): RecyclerView.Adapter<DisciplinaListAdapter.ViewHolder>() {

    class ViewHolder(
        private val binding: DisciplinaItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(disciplina: DisciplinaItem) {
            binding.titleTextView.text = disciplina.descricao
        }
    }

    private val asyncListDiffer: AsyncListDiffer<DisciplinaItem> = AsyncListDiffer(this, DiffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DisciplinaItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = asyncListDiffer.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(asyncListDiffer.currentList[position])
    }

    fun updateDisciplinas(disciplinas: List<DisciplinaItem>) {
        asyncListDiffer.submitList(disciplinas)
    }

    object DiffCallback : DiffUtil.ItemCallback<DisciplinaItem>() {

        override fun areItemsTheSame(oldItem: DisciplinaItem, newItem: DisciplinaItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DisciplinaItem, newItem: DisciplinaItem): Boolean {
            return oldItem.descricao == newItem.descricao
        }
    }
}