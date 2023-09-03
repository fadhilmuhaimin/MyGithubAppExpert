package com.fadhil.core.uiCore

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fadhil.core.R
import com.fadhil.core.data.remote.response.ItemsItem
import com.fadhil.core.databinding.ItemUserBinding


class SearchAdapter(private val state : Boolean) : ListAdapter<ItemsItem, SearchAdapter.MyViewHolder>(
    DIFF_CALLBACK
) {

    var onItemClick: ((ItemsItem) -> Unit)? = null

    class MyViewHolder(val binding : ItemUserBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: ItemsItem, state: Boolean, onItemClick: ((ItemsItem) -> Unit)?){

            if (state){
                binding.root.setOnClickListener {
//                    itemView.context.startActivity(Intent(itemView.context,DetailActivity::class.java)
//                        .putExtra(DetailActivity.KEY_DETAIL,item))
                    onItemClick?.invoke(item)
                }
            }



            binding.tvProfile.text = item.login
            Glide.with(itemView.context)
                .load(item.avatarUrl)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(binding.imgProfile)


        }
    }


    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<ItemsItem> =
            object : DiffUtil.ItemCallback<ItemsItem>() {
                override fun areItemsTheSame(oldUser: ItemsItem, newUser: ItemsItem): Boolean {
                    return oldUser.id == newUser.id
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(oldUser: ItemsItem, newUser: ItemsItem): Boolean {
                    return oldUser == newUser
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user,state,onItemClick)
    }
}