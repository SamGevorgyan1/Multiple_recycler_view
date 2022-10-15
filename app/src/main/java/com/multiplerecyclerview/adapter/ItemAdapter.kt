package com.multiplerecyclerview.adapter


import android.content.Context

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.multiplerecyclerview.MainActivity

import com.multiplerecyclerview.R
import com.multiplerecyclerview.model.DataModel


class ItemAdapter(val context: Context) : RecyclerView.Adapter<ItemAdapter.MyViewHolder>() {

    private val adapterData = mutableListOf<DataModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = when (viewType) {
            TYPE_IMAGE -> R.layout.item_image
            TYPE_TEXT -> R.layout.item_text
            TYPE_WEB_VIEW -> R.layout.item_web_view
            TYPE_VIDEO -> R.layout.item_video
            else ->{R.layout.activity_main}
        }

        val view = LayoutInflater
            .from(parent.context)
            .inflate(layout, parent, false)
        return MyViewHolder(view)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(adapterData[position])
    }

    override fun getItemCount(): Int = adapterData.size

    override fun getItemViewType(position: Int): Int {
        return when (adapterData[position]) {
            is DataModel.ImageUrl -> TYPE_IMAGE
            is DataModel.TextPost -> TYPE_TEXT
            is DataModel.WebViewPost-> TYPE_WEB_VIEW
        }
    }
    fun setData(data: List<DataModel>) {
        adapterData.addAll(data)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val main=MainActivity()

        private val img=itemView.findViewById<ImageView>(R.id.image_id)
        private val textpost=itemView.findViewById<TextView>(R.id.text_id)
        private val imgWebUrl=itemView.findViewById<ImageView>(R.id.webViewImage)
        private val webUrl=itemView.findViewById<TextView>(R.id.webViewUrl)
/**
        init {
            webUrl.setOnClickListener{main.intent()}

        }
        **/

        private fun bindImagePost(item: DataModel.ImageUrl) {
            Glide.with(context)
                .load(item.imageUrl)
                .into(img)
        }

        private fun bindTextPost(item: DataModel.TextPost) {
            textpost.text = item.textPost
        }
        private fun bindWebView(item:DataModel.WebViewPost){
            Glide.with(context)
                .load(item.webImgUrl)
                .into(imgWebUrl)
            webUrl.text=item.webViewUrl
        }

        fun bind(dataModel: DataModel) {
            when (dataModel) {
                is DataModel.ImageUrl -> bindImagePost(dataModel)
                is DataModel.TextPost -> bindTextPost(dataModel)
                is DataModel.WebViewPost->bindWebView(dataModel)

            }
        }
    }
    companion object {
        private const val TYPE_IMAGE = 0
        private const val TYPE_TEXT = 1
        private const val TYPE_WEB_VIEW = 2
        private const val TYPE_VIDEO = 3
    }
    }










