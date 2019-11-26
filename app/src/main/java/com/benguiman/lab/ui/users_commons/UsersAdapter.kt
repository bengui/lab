package com.benguiman.lab.ui.users_commons

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.benguiman.lab.R
import com.benguiman.lab.ui.UserUi

class UsersAdapter(var userList: List<UserUi> = listOf()) : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.user_text_view,
                parent,
                false
            ) as TextView
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.textView.text = userList[position].name
    }


    class UserViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
}