package com.example.to_do_list.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(TodoEntity::class),version = 1) // 데이터 베이스 조건1-어노테이션
abstract class AppDatabase : RoomDatabase() { // 조건2, room 데이터 베이스 상속 받아야한다
    abstract fun getTodoDao() : TodoDao // 조건3 Dao를 반환하고 인수가 존재하지 않는 추상함수가있어야한다

    companion object { //싱글톤 패턴-프로그램내에서 하나의 객체를 공유하는 디자인 패턴
        val databaseName = "db_todo" //데이터베이스 이름
        var appDatabase : AppDatabase? = null

        fun getInstance(context : Context) : AppDatabase? {
            if(appDatabase == null){
                appDatabase = Room.databaseBuilder(context,
                    AppDatabase::class.java,
                    databaseName).fallbackToDestructiveMigration().build()
            }
            return appDatabase
        }
    }
}

// 데이터 베이스 객체 생성에는 비용이 들기에 싱글톤 패턴 사용
// 컴패니언 오브젝트 - 어떤 클래스의 모든 인스턴스가 공유하는 객체를 만들고 싶을 때 사용하며 클래스 당 1개만 가능
// 인스턴스는 객체 지향 프로그래밍에서 클래스라는 설계도를 바당으로 생성된 실제 메모리상의 객체를 의미
//인스턴스의 특징은 독립적인 상태: 각 인스턴스는 고유한 변수 값을 가짐, 예시 자동차 클래스의 인스턴스 빨간차, 파란차
// 독립적인 동작: 각 인스턴스는 독립저긍로 메서드를 실행할수 있음
// 메모리 할당