package com.harsha.buildlinkbetweenadeptandcompanies.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.harsha.buildlinkbetweenadeptandcompanies.Models.Post
import com.harsha.buildlinkbetweenadeptandcompanies.Models.User
import com.harsha.buildlinkbetweenadeptandcompanies.R
import com.harsha.buildlinkbetweenadeptandcompanies.databinding.PostRvBinding
import com.harsha.buildlinkbetweenadeptandcompanies.utils.USER_NODE

class PostAdapter(var context:Context, var postList:ArrayList<Post>):RecyclerView.Adapter<PostAdapter.Myholder>() {
    inner class Myholder(var binding: PostRvBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myholder {
        var binding=PostRvBinding.inflate(LayoutInflater.from(context),parent,false)
        return Myholder(binding)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: Myholder, position: Int) {
        Glide.with(context).load(postList.get(position).postUrl).placeholder(R.drawable.loading).into(holder.binding.postImage)
        holder.binding.caption.text=postList.get(position).caption
        holder.binding.like.setOnClickListener {
            holder.binding.like.setImageResource(R.drawable.like_filled)
        }
        holder.binding.praise.setOnClickListener {
            holder.binding.praise.setImageResource(R.drawable.praise_filled)
        }
        holder.binding.share.setOnClickListener{
            var i=Intent(android.content.Intent.ACTION_SEND)
            i.type="text/plain"
            i.putExtra(Intent.EXTRA_TEXT,postList.get(position).postUrl)
            context.startActivity(i)
        }
    }
}