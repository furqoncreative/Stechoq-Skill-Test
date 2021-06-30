package com.stechoq.todo.core.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stechoq.todo.core.R
import com.stechoq.todo.core.databinding.ItemListTodoBinding
import com.stechoq.todo.core.domain.model.Todo
import java.util.*

class TodosAdapter : RecyclerView.Adapter<TodosAdapter.ListViewHolder>() {

    private var listData = ArrayList<Todo>()
    var onItemClick: ((Todo) -> Unit)? = null

    fun setData(newListData: List<Todo>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_todo, parent, false),
            parent.context
        )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View, val context: Context) :
        RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListTodoBinding.bind(itemView)

        @SuppressLint("NewApi")
        fun bind(data: Todo) {
            binding.apply {
                tvTask.text = data.title
                if (data.completed == true) {
                    tvStatus.apply {
                        text = "Completed"
                        backgroundTintList = context.getColorStateList(R.color.green)
                    }
                } else {
                    tvStatus.apply {
                        text = "TODO"
                        tvStatus.backgroundTintList = context.getColorStateList(
                            R.color.orange
                        )
                    }
                }
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[absoluteAdapterPosition])
            }
        }
    }
}