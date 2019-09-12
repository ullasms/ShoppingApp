package com.ullasonlineshop.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.ullasonlineshop.R
import com.ullasonlineshop.models.Product
import com.ullasonlineshop.utils.loadImage
import kotlinx.android.synthetic.main.item_horizontal_free_scroll.view.*

class HorizontalFreeScrollAdapter(private val requestManager: RequestManager, private val items: List<Product>) :
    RecyclerView.Adapter<HorizontalFreeScrollAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_horizontal_free_scroll,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(requestManager, items[position])
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(requestManager: RequestManager, data: Product) {
            itemView.item_horizontal_image.loadImage(requestManager, data.imageURL)
            itemView.item_horizontal_title.text = data.name
            itemView.item_horizontal_price.text = data.price
            itemView.item_horizontal_ideal_for.text = data.type
        }
    }
}