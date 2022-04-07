package br.ifsc.edu.crud.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.ifsc.edu.crud.R
import br.ifsc.edu.crud.models.User
import coil.load
import java.io.File

class RepoListAdapter(private val itemClick: (User) -> Unit) :
    RecyclerView.Adapter<UserListViewHolder>() {
    private var userList = mutableListOf<User>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user_list, parent, false)
        return UserListViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.bind(userList[position])
        holder.itemView.setOnClickListener {
            itemClick(userList[position])
        }
    }

    override fun getItemCount() = userList.size

    fun update(newList: MutableList<User>, clearList: Boolean) {
        if (clearList) {
            userList.clear()
        }

        userList.addAll(newList)
        notifyDataSetChanged()
    }
}

class UserListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(user: User) {

        itemView.findViewById<TextView>(R.id.tv_name).apply {
            text = user.nome
        }

        itemView.findViewById<TextView>(R.id.tv_email).apply {
            text = user.email
        }

        itemView.findViewById<ImageView>(R.id.imageView).apply {
            this.load(File(user.foto))
        }
    }
}