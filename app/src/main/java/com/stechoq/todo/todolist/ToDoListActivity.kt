package com.stechoq.todo.todolist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.stechoq.todo.R
import com.stechoq.todo.core.data.Resource
import com.stechoq.todo.core.ui.TodosAdapter
import com.stechoq.todo.databinding.ActivityToDoListBinding
import com.stechoq.todo.tododetail.ToDoDetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class ToDoListActivity : AppCompatActivity() {
    lateinit var binding: ActivityToDoListBinding
    private val viewModel: TodoListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToDoListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTodosData()
    }

    private fun setTodosData() {
        val todosAdapter = TodosAdapter()
        todosAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, ToDoDetailActivity::class.java)
            intent.putExtra(ToDoDetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        viewModel.todos.observe(this, { todos ->
            if (todos != null) {
                when (todos) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        val result = todos.data

                        if (!result.isNullOrEmpty()) {
                            todosAdapter.setData(result)
                        } else {
                            binding.viewEmpty.tvEmpty.text =
                                StringBuilder(getString(R.string.no_data))
                            binding.viewEmpty.root.visibility = View.VISIBLE
                        }
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.viewError.root.visibility = View.VISIBLE
                        binding.viewError.tvError.text =
                            todos.message ?: getString(R.string.something_wrong)
                        Log.e("TAG", "setTodosData: ${todos.message}")

                    }
                }
            }
        })

        with(binding.rvRecipes) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = todosAdapter
        }
    }
}