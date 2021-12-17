package com.excite.inventoryapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.excite.inventoryapp.data.Item
import com.excite.inventoryapp.data.getFormattedPrice
import com.excite.inventoryapp.databinding.FragmentItemDetailBinding
import com.excite.inventoryapp.databinding.ItemListFragmentBinding

/**
 * [ListAdapter] implementation for the recyclerview.
 */

class ItemListAdapter(private val onItemClicked: (Item) -> Unit) :
    ListAdapter<Item, ItemListAdapter.ItemViewHolder>(DiffCallback) {

        class ItemViewHolder(private var binding: ItemListFragmentBinding):
            RecyclerView.ViewHolder(binding.root) {

                fun bind(item: Item) {
                    binding.itemName.text = item.itemName
                    binding.itemPrice.text = item.getFormattedPrice()
                    binding.itemQuantity.text = item.quantityInStock.toString()
                }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemListFragmentBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.bind(current)
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.itemName == newItem.itemName
            }

        }
    }
}