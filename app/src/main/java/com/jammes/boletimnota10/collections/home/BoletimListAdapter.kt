package com.jammes.boletimnota10.collections.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jammes.boletimnota10.collections.model.BoletimItem
import com.jammes.boletimnota10.databinding.BoletimItemBinding

class BoletimListAdapter(): RecyclerView.Adapter<BoletimListAdapter.ViewHolder>() {

    class ViewHolder(
        private val binding: BoletimItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(boletim: BoletimItem) {
            binding.disciplinaTextView.text = boletim.disciplina
            binding.root.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToNavAvaliacoes(boletim.periodoId, boletim.id)
                itemView.findNavController().navigate(action)
            }
        }
    }

    private val asyncListDiffer: AsyncListDiffer<BoletimItem> = AsyncListDiffer(this, DiffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = BoletimItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = asyncListDiffer.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(asyncListDiffer.currentList[position])
    }

    fun buscarBoletim(boletim: List<BoletimItem>) {
        asyncListDiffer.submitList(boletim)
    }

    object DiffCallback : DiffUtil.ItemCallback<BoletimItem>() {

        override fun areItemsTheSame(oldItem: BoletimItem, newItem: BoletimItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BoletimItem, newItem: BoletimItem): Boolean {
            return oldItem.disciplina == newItem.disciplina
        }
    }

}