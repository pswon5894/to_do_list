package com.example.to_do_list

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.to_do_list.databinding.ActivityMainBinding
import com.example.to_do_list.db.AppDatabase
import com.example.to_do_list.db.TodoDao
import com.example.to_do_list.db.TodoEntity

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    //var 변수명 : 변수타입 = 초기화

    private lateinit var db : AppDatabase
    private lateinit var todoDao : TodoDao
    private lateinit var todoList : ArrayList<TodoEntity>
    private lateinit var adapter : TodoRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        db = AppDatabase.getInstance(this)!!
        todoDao = db.getTodoDao()

        getAllTodoList()
    }

    private fun getAllTodoList(){
        Thread{
            todoList = ArrayList(todoDao.getAllTodo())
            setRecyclerView()
        }.start()
    }

    private fun setRecyclerView(){
        runOnUiThread {
            adapter = TodoRecyclerViewAdapter(todoList)
            binding.recyclerview.adapter = adapter
            binding.recyclerview.layoutManager = LinearLayoutManager(this)
        }
    }

    override fun onRestart() {
        super.onRestart()
        getAllTodoList()
    }
}

//리사이클러뷰 1.어댑터-아이템뷰와 데이터를 바인딩, 2.레이아웃 매니저-아이템뷰의배치를 결정