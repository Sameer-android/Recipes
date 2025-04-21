package com.example.recipes.fragments.chat

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipes.databinding.RecieverMessageBinding
import com.example.recipes.databinding.SendMessageBinding
import com.example.recipes.fragments.models.Message
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter(val context: Context, private val messageList:ArrayList<Message>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        val ITEM_RECEIVE = 1
        val ITEM_SEND = 2



     inner class sendMessage(var binding: SendMessageBinding):RecyclerView.ViewHolder(binding.root)
     inner class recieveMessage(var binding: RecieverMessageBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1){
            //here inflate receive
            var binding = RecieverMessageBinding.inflate(LayoutInflater.from(context),parent,false)
            return recieveMessage(binding)
        }else{
            //here inflate send
            var binding = SendMessageBinding.inflate(LayoutInflater.from(context),parent,false)
            return sendMessage(binding)
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentMessage = messageList[position]
        if (holder.javaClass == sendMessage::class.java){
            //send ViewHolder Here.
            val viewHolder = holder as sendMessage
            holder.binding.tvSendMessage.text = currentMessage.message
        }else{
            //receive ViewHolder Here.
            val viewHolder = holder as recieveMessage
            holder.binding.tvReceiverMessage.text = currentMessage.message
        }
    }

    override fun getItemViewType(position: Int): Int {
        val currentMessage = messageList[position]
        if (FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId)){
            return ITEM_SEND
        } else{
            return ITEM_RECEIVE
        }
    }
}