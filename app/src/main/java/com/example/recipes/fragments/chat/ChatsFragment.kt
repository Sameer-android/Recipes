package com.example.recipes.fragments.chat

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLayoutChangeListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipes.databinding.FragmentChatsBinding
import com.example.recipes.fragments.models.Message
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ChatsFragment : Fragment() {
    private lateinit var binding: FragmentChatsBinding
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var messageList: ArrayList<Message>
    private lateinit var mDbRef: DatabaseReference

    var recieverRoom: String? = null
    var senderRoom: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentChatsBinding.inflate(inflater, container, false)

        mDbRef = FirebaseDatabase.getInstance().getReference()

        val name = requireArguments().getString("name")
        val receiverUid = requireArguments().getString("uid")
        binding.nameOfChatUser.text = name

        val senderUid = FirebaseAuth.getInstance().currentUser?.uid
        senderRoom = receiverUid + senderUid
        recieverRoom = senderUid + receiverUid

        messageList = ArrayList()
        messageAdapter = MessageAdapter(requireContext(), messageList)
        binding.chatRv.layoutManager = LinearLayoutManager(requireActivity())
        binding.chatRv.adapter = messageAdapter


        //logic for read data in recyclerView.
        mDbRef.child("chats").child(senderRoom!!).child("messages")
            .addValueEventListener(object : ValueEventListener {
                @SuppressLint("NotifyDataSetChanged")
                override fun onDataChange(snapshot: DataSnapshot) {
                    messageList.clear()

                    for (postSnapshot in snapshot.children) {
                        val message = postSnapshot.getValue(Message::class.java)
                        messageList.add(message!!)
                    }

                    messageAdapter.notifyDataSetChanged()
                    binding.chatRv.scrollToPosition(messageList.size - 1)
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })

        binding.chatRv.addOnLayoutChangeListener { v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
            binding.chatRv.scrollToPosition(
                messageList.size - 1
            )
        }
        //adding the message to database
        binding.sendMessageBtn.setOnClickListener {
            val message = binding.typeMessage.text.toString()
            val messageObject = Message(message, senderUid)
            if (message.isEmpty()) {
                Toast.makeText(requireContext(), "Please Enter Some Message", Toast.LENGTH_SHORT)
                    .show()
            } else {
                mDbRef.child("chats").child(senderRoom!!).child("messages").push()
                    .setValue(messageObject).addOnSuccessListener {
                        mDbRef.child("chats").child(recieverRoom!!).child("messages").push()
                            .setValue(messageObject)
                    }
                binding.typeMessage.setText("")
                binding.chatRv.smoothScrollToPosition(messageList.size)
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Checking","onViewCreated Call")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Checking","onResume Call")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Checking","onDestroy Call")
    }
}