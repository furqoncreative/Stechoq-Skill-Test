package com.stechoq.todo.tododetail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.stechoq.todo.core.domain.model.Todo
import com.stechoq.todo.databinding.ActivityToDoDetailBinding

class ToDoDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityToDoDetailBinding

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToDoDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        val data = intent.getParcelableExtra<Todo>(EXTRA_DATA)
        if (data != null) {
            binding.apply {

                tvIdTask.text = data.id.toString()
                tvIdUser.text = data.userId.toString()
                tvTask.text = data.title
                if (data.completed == true) {
                    tvStatus.apply {
                        text = "Completed"
                        backgroundTintList =
                            getColorStateList(com.stechoq.todo.core.R.color.green)
                    }
                } else {
                    tvStatus.apply {
                        text = "TODO"
                        tvStatus.backgroundTintList = getColorStateList(
                            com.stechoq.todo.core.R.color.orange
                        )
                    }
                }
            }

        }
    }


    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}