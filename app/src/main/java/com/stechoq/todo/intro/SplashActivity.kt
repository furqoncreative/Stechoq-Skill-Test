package com.stechoq.todo.intro

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.stechoq.todo.R
import com.stechoq.todo.databinding.ActivitySplashBinding
import com.stechoq.todo.todolist.ToDoListActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        Glide.with(applicationContext)
            .load(R.drawable.logo)
            .into(binding.ivSplash)

        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, ToDoListActivity::class.java))
            finish()
        }, 3000)
    }
}