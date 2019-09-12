package com.ullasonlineshop.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.ullasonlineshop.R
import com.ullasonlineshop.models.Content
import com.ullasonlineshop.models.Product
import com.ullasonlineshop.utils.loadImage
import kotlinx.android.synthetic.main.layout_banner.view.*
import kotlinx.android.synthetic.main.layout_horizontal_scroll.view.*
import kotlinx.android.synthetic.main.layout_split_banner.view.*

class ContentAdapter(private val requestManager: RequestManager, private val items: List<Content>) :
    RecyclerView.Adapter<ContentAdapter.RootVH>() {

    private val TYPE_HORIZONTAL_SCROLL = 1
    private val TYPE_BANNER = 2
    private val TYPE_SPLIT_BANNER = 3

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        if (items[position].sectionType.equals("horizontalFreeScroll"))
            return TYPE_HORIZONTAL_SCROLL
        else if (items[position].sectionType.equals("banner"))
            return TYPE_BANNER
        return TYPE_SPLIT_BANNER
    }

    override fun onBindViewHolder(holder: RootVH, position: Int) {
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RootVH {
        when (viewType) {
            TYPE_HORIZONTAL_SCROLL ->
                return HorizontalFreeScrollVH(
                    requestManager,
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.layout_horizontal_scroll,
                        parent,
                        false
                    )
                )

            TYPE_BANNER ->
                return BannerVH(
                    requestManager,
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.layout_banner,
                        parent,
                        false
                    )
                )
            else ->
                return SplitBannerVH(
                    requestManager,
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.layout_split_banner,
                        parent,
                        false
                    )
                )
        }
    }

    abstract class RootVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(data: Content)
    }

    class HorizontalFreeScrollVH(private var requestManager: RequestManager, itemView: View) : RootVH(itemView) {
        private val productItems = mutableListOf<Product>()
        private val adapter = HorizontalFreeScrollAdapter(requestManager, productItems)

        init {
            initRecyclerView()
        }

        override fun bind(data: Content) {
            itemView.horizontal_title.text = data.name
            data.products?.let {
                productItems.clear()
                productItems.addAll(it)
                adapter.notifyDataSetChanged()
            }
        }

        private fun initRecyclerView() {
            itemView.horizontal_recyclerView.layoutManager =
                LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
            itemView.horizontal_recyclerView.adapter = adapter
        }
    }

    class BannerVH(private var requestManager: RequestManager, itemView: View) : RootVH(itemView) {
        override fun bind(data: Content) {
            itemView.item_banner_image.loadImage(requestManager, data.bannerImage)
        }
    }

    class SplitBannerVH(private var requestManager: RequestManager, itemView: View) : RootVH(itemView) {
        override fun bind(data: Content) {
            itemView.item_split_first_image.loadImage(requestManager, data.firstImage)
            itemView.item_split_second_image.loadImage(requestManager, data.secondImage)
        }
    }
}