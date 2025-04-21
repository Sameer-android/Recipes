package com.example.recipes.fragments.chat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.databinding.UserItemviewBinding
import com.example.recipes.fragments.models.User

class UserAdapter(var context: Context, private var userList: ArrayList<User>, private val onItemClick:(String, String) -> Unit) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = UserItemviewBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentUser = userList[position]
        anim(holder.itemView)
        holder.binding.nameTextView.text = currentUser.name
        holder.itemView.setOnClickListener {
            onItemClick(currentUser.name.toString(), currentUser.uid.toString())
        }
    }


    inner class ViewHolder(var binding: UserItemviewBinding) : RecyclerView.ViewHolder(binding.root)

    private fun anim(view: View) {
        val animation = AlphaAnimation(0.0f, 1.0f)
        animation.duration = 1000
        view.startAnimation(animation)
    }
}