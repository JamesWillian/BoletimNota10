package com.jammes.boletimnota10.collections.turma

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jammes.boletimnota10.collections.model.ModuloItem
import com.jammes.boletimnota10.collections.model.PeriodoItem
import com.jammes.boletimnota10.databinding.PeriodoItemBinding

class PeriodoListAdapter: RecyclerView.Adapter<PeriodoListAdapter.ViewHolder>() {

    class ViewHolder(
        private val binding: PeriodoItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(periodo: PeriodoItem) {
            binding.periodoTextView.text = periodo.periodo
        }
    }

    private val asyncListDiffer: AsyncListDiffer<PeriodoItem> = AsyncListDiffer(this, DiffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PeriodoItemBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = asyncListDiffer.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(asyncListDiffer.currentList[position])
    }

    fun updatePeriodos(periodos: List<PeriodoItem>) {
        asyncListDiffer.submitList(periodos)
    }

    object DiffCallback : DiffUtil.ItemCallback<PeriodoItem>() {

        override fun areItemsTheSame(oldItem: PeriodoItem, newItem: PeriodoItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PeriodoItem, newItem: PeriodoItem): Boolean {
            return oldItem.periodo == newItem.periodo
        }
    }
}