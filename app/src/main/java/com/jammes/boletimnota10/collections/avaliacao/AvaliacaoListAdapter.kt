package com.jammes.boletimnota10.collections.avaliacao

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jammes.boletimnota10.databinding.AvaliacaoItemBinding
import com.jammes.boletimnota10.collections.model.AvaliacaoItem

class AvaliacaoListAdapter(
    private val viewModel: AvaliacaoViewModel,
    private val fragmentManager: FragmentManager
) : RecyclerView.Adapter<AvaliacaoListAdapter.ViewHolder>() {

    class ViewHolder(
        private val binding: AvaliacaoItemBinding,
        private val viewModel: AvaliacaoViewModel,
        private val fragmentManager: FragmentManager
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(avaliacao: AvaliacaoItem) {
            binding.descricaoAvaliacaoTextView.text = avaliacao.descricao
            binding.notaAvaliacaoTextView.text = avaliacao.nota
            binding.dataAvaliacaoTextView.text = avaliacao.data

            binding.root.setOnClickListener {
                AvaliacaoFormFragment(
                    viewModel,
                    avaliacao.moduloId)
                    .show(
                        fragmentManager, "AvaliacaoFormDialog"
                    )
            }
        }

    }

    private val asyncListDiffer: AsyncListDiffer<AvaliacaoItem> =
        AsyncListDiffer(this, DiffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AvaliacaoItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, viewModel, fragmentManager)
    }

    override fun getItemCount(): Int = asyncListDiffer.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(asyncListDiffer.currentList[position])
    }

    fun recarregarAvaliacoes(avaliacao: List<AvaliacaoItem>) {
        asyncListDiffer.submitList(avaliacao)
    }

    object DiffCallback : DiffUtil.ItemCallback<AvaliacaoItem>() {

        override fun areItemsTheSame(oldItem: AvaliacaoItem, newItem: AvaliacaoItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AvaliacaoItem, newItem: AvaliacaoItem): Boolean {
            return oldItem.descricao == newItem.descricao
        }
    }


}