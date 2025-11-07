package com.example.to_do_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.to_do_list.databinding.ItemTodoBinding
import com.example.to_do_list.db.TodoEntity

class TodoRecyclerViewAdapter (private val todoList : ArrayList<TodoEntity>) : RecyclerView.Adapter<TodoRecyclerViewAdapter.MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding : ItemTodoBinding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val todoData = todoList[position]

        when (todoData.importance){
            1->{
                holder.tv_importance.setBackgroundResource(R.color.red)
            }
            2->{
                holder.tv_importance.setBackgroundResource(R.color.yellow)
            }

            3->{
                holder.tv_importance.setBackgroundResource(R.color.green)
            }
        }
        holder.tv_importance.text = todoData.importance.toString()
        holder.tv_title.text = todoData.title
    }

    override fun getItemCount(): Int {
    }

    inner class MyViewHolder(binding : ItemTodoBinding) : RecyclerView.ViewHolder(binding.root){
        val tv_importance = binding.tvImportance
        val tv_title = binding.tvTitle

        val root = binding.root
    }
}

//androidx.constraintlayout.widget.ConstraintLayout
//root 바인딩 루트 레이아웃

//class TodoRecyclerViewAdapter (private val todoList : ArrayList<TodoEntity>) : RecyclerView.Adapter<TodoRecyclerViewAdapter.MyViewHolder(){
//뷰홀더 패턴-각 객체를 뷰폴더에 보관해서 반복 메서드 호출을 줄여 속도를 개선하는 패턴
//RecyclerView.Adapter 리사이클러뷰에서 어뎁터 상속
//https://velog.io/@haero_kim/Android-ViewHolder-%ED%8C%A8%ED%84%B4%EC%9D%84-%EC%93%B0%EB%8A%94-%EC%9D%B4%EC%9C%A0