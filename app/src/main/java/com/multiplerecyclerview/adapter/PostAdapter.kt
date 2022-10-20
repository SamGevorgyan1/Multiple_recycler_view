package com.multiplerecyclerview.adapter


import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.multiplerecyclerview.R
import com.multiplerecyclerview.model.PostDataModel



class PostAdapter(val clickListenerWebView:(PostDataModel.WebViewPost)->Unit) : RecyclerView.Adapter<PostAdapter.MyViewHolder>() {
    private val itemsPost: MutableList<PostDataModel> = mutableListOf()


    private lateinit var context: Context
    private lateinit var layoutInflater: LayoutInflater


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        context = recyclerView.context
        layoutInflater = LayoutInflater.from(context)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val layout = when (viewType) {
            VIEW_TYPE_IMAGE_POST -> R.layout.item_image
            VIEW_TYPE_TEXT_POST -> R.layout.item_text
            VIEW_TYPE_WEB_POST -> R.layout.item_web_view
            VIEW_TYPE_VIDEO_POST -> R.layout.item_video
            else -> throw IllegalArgumentException("undefined viewType: $viewType in ${this::class.java.simpleName}")
        }

        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(itemsPost[position])
    }

    override fun getItemCount(): Int = itemsPost.size

    override fun getItemViewType(position: Int): Int {
        return when (itemsPost[position]) {
            is PostDataModel.ImagePost -> VIEW_TYPE_IMAGE_POST
            is PostDataModel.TextPost -> VIEW_TYPE_TEXT_POST
            is PostDataModel.WebViewPost -> VIEW_TYPE_WEB_POST
        }
    }

    fun updateData(data: MutableList<PostDataModel>) {
        this.itemsPost.clear()
        this.itemsPost.addAll(data)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val imagePost = itemView.findViewById<ImageView>(R.id.image_post_id)
        private val textPost = itemView.findViewById<TextView>(R.id.text_post_id)
        private val imgWebUrlPost = itemView.findViewById<ImageView>(R.id.web_View_Image_post_id)
        private val webUrlPost = itemView.findViewById<TextView>(R.id.web_view_url_post)

        private fun bindImagePost(
            item: PostDataModel.ImagePost,

        ) {
            Glide.with(context)
                .load(item.imagePostUrl)
                .into(imagePost)
        }

        private fun bindTextPost(item: PostDataModel.TextPost) {
            textPost.text = item.textPost

        }

        private fun bindWebView(item: PostDataModel.WebViewPost,clickListener:(PostDataModel.WebViewPost)->Unit) {

            Glide.with(context)
                .load(item.webImgUrl)
                .into(imgWebUrlPost)

            webUrlPost.text = item.webViewUrl

            webUrlPost.setOnClickListener{clickListener(item)}
        }

        fun bind(dataModel: PostDataModel) {
            when (dataModel) {
                is PostDataModel.ImagePost -> bindImagePost(dataModel)
                is PostDataModel.TextPost -> bindTextPost(dataModel)
                is PostDataModel.WebViewPost -> bindWebView(dataModel,clickListenerWebView)

            }
        }
    }

    companion object {
        private const val VIEW_TYPE_IMAGE_POST = 0
        private const val VIEW_TYPE_TEXT_POST = 1
        private const val VIEW_TYPE_WEB_POST = 2
        private const val VIEW_TYPE_VIDEO_POST = 3

    }
}










