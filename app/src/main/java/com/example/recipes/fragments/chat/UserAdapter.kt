package com.example.recipes.fragments.chat

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.databinding.UserItemviewBinding
import com.example.recipes.fragments.models.User

class UserAdapter(var context:Context,var userList:ArrayList<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = UserItemviewBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentUser = userList[position]
        holder.binding.nameTextView.text = currentUser.name
    }


    inner class ViewHolder(var binding: UserItemviewBinding) : RecyclerView.ViewHolder(binding.root)
}