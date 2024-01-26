package com.jammes.boletimnota10.collections.turma

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jammes.boletimnota10.databinding.TurmaItemBinding
import com.jammes.boletimnota10.collections.model.TurmaItem

class TurmaListAdapter(): RecyclerView.Adapter<TurmaListAdapter.ViewHolder>() {

    class ViewHolder(
        private val binding: TurmaItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(turmaItem: TurmaItem) {

            binding.turmaTextView.text = turmaItem.nome
            binding.escolaTextView.text = turmaItem.escola
            binding.turnoTextView.text = turmaItem.turno
            binding.anoTextView.text = turmaItem.ano
            if (turmaItem.concluido)
                binding.concluidoTextView.visibility = View.GONE

            binding.root.setOnClickListener {
                itemView.findNavController().navigate(TurmaFragmentDirections.actionNavTurmaToNavFormTurma(turmaItem.id))
            }
        }
    }

    private val asyncListDiffer: AsyncListDiffer<TurmaItem> = AsyncListDiffer(this, DiffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = TurmaItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = asyncListDiffer.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(asyncListDiffer.currentList[position])
    }

    fun updateTurmas(turmas: List<TurmaItem>) {
        asyncListDiffer.submitList(turmas)
    }

    object DiffCallback : DiffUtil.ItemCallback<TurmaItem>() {

        override fun areItemsTheSame(oldItem: TurmaItem, newItem: TurmaItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TurmaItem, newItem: TurmaItem): Boolean {
            return oldItem.nome == newItem.nome
        }
    }
}