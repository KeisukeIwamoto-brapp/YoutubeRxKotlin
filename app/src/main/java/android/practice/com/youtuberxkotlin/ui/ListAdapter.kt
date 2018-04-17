package android.practice.com.youtuberxkotlin.ui

import android.databinding.BindingAdapter
import android.practice.com.youtuberxkotlin.databinding.ItemVideoBinding
import android.practice.com.youtuberxkotlin.vo.Video
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso


class ListAdapter() : RecyclerView.Adapter<ListAdapter.BindingHolder>() {
    var dataList: MutableList<Video> = mutableListOf()
    lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder {
        setOnItemClickListener(listener)
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = ItemVideoBinding.inflate(layoutInflater, parent, false)

        return BindingHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingHolder, position: Int) {
        val data = dataList[position]
        holder.binding.setData(data)
        holder.binding.originalLinearLayout.setOnClickListener({
            listener.onClick(it, data)
        })
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun clear() {
        dataList.clear()
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onClick(view: View, data: Video)
    }

    fun addItems(data: List<Video>) {
        for (v in data) {
            dataList.add(v)
            notifyItemInserted(dataList.size - 1)
        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    object ImageViewBindingAdapter {
        @JvmStatic
        @BindingAdapter("bind:imageUrl")
        fun loadImage(view: ImageView, url: String) {
            Picasso.with(view.context).load(url).into(view)
        }
    }

    class BindingHolder(var binding: ItemVideoBinding) : RecyclerView.ViewHolder(binding.root)

}