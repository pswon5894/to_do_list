package com.example.to_do_list

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.to_do_list.databinding.ActivityAddTodoBinding
import com.example.to_do_list.db.AppDatabase
import com.example.to_do_list.db.TodoDao
import com.example.to_do_list.db.TodoEntity

class AddTodoActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAddTodoBinding
    lateinit var db : AppDatabase
    lateinit var todoDao: TodoDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityAddTodoBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)!!
        todoDao = db.getTodoDao()

        binding.btnComplete.setOnClickListener{
            insertTodo()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun insertTodo() {
        val todoTitle = binding.editTitle.text.toString()
        var todoImportance = binding.radioGroup.checkedRadioButtonId

        var impData = 0;
        when(todoImportance){
            R.id.btn_high -> {
                impData = 1;
            }
            R.id.btn_middle ->{
                impData = 2;
            }
            R.id.btn_low ->{
                impData = 3;
            }
        }
        if(impData ==0 || todoTitle.isBlank()) {
            Toast.makeText(this, "모든 항목을 채워주세요.", Toast.LENGTH_SHORT).show()
        }else{
            Thread{
                todoDao.insertTodo(TodoEntity(null, todoTitle,impData))
                runOnUiThread {
                    Toast.makeText(this, "할 일이 추가되었습니다.", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }.start()
        }
    }
}

//의미:시스템 바 영역만큼 패딩을 주어UI가 겹치지 않게 하는 코드
// 삭제 가능 여부: 레이아웃이 시스템 바와 겹치지 않는다면 지워도 무방, 하지만 겹친다면 유지하는게 안전

//db = AppDatabase.getInstance(this)!! 싱글톤 패턴