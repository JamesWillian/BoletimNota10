package com.jammes.boletimnota10.ui.turma

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jammes.boletimnota10.databinding.TurmaDisciplinaItemBinding
import com.jammes.boletimnota10.ui.model.TurmaDisciplinaItem

class TurmaDisciplinaListAdapter(
    private val viewModel: TurmaViewModel
): RecyclerView.Adapter<TurmaDisciplinaListAdapter.ViewHolder>() {
    class ViewHolder(
        private val binding: TurmaDisciplinaItemBinding,
        private val viewModel: TurmaViewModel
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(disciplina: TurmaDisciplinaItem) {
            binding.titleTextView.text = disciplina.disciplina

            binding.checkbox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) viewModel.disciplinasSelecionadas.add(disciplina)
                else viewModel.disciplinasSelecionadas.remove(disciplina)
            }
        }
    }

    private val asyncListDiffer: AsyncListDiffer<TurmaDisciplinaItem> = AsyncListDiffer(this, DiffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TurmaDisciplinaItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, viewModel)
    }

    override fun getItemCount(): Int = asyncListDiffer.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(asyncListDiffer.currentList[position])
    }

    fun updateDisciplinas(disciplinas: List<TurmaDisciplinaItem>) {
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