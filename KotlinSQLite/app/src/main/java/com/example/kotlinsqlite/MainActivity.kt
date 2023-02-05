package com.example.kotlinsqlite

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // databas eislmelerinde try catch kullanmamiz gerekir.
        try {
            // activity'den turetecegimiz icin thid diyorum.
            // Onceden database varsaa ac yoksa olustur..
            val myDatabase=this.openOrCreateDatabase("Musicians", Context.MODE_PRIVATE,null)
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS musicians (name VARCHAR, age INT)")
            myDatabase.execSQL("INSERT INTO musicians (name,age) VALUES ('Hicran',22)")

            //kaydettigimiz seyi cekmek icin bir imlec(cursor) ile calisiyoruz.
            val cursor=myDatabase.rawQuery("SELECT * FROM musicians",null)

            val nameIndex=cursor.getColumnIndex("name")
            val ageIndex=cursor.getColumnIndex("age")

            // gezebildigi kadar bu isleme devam ett
            while (cursor.moveToNext()){
                println("Name: "+cursor.getString(nameIndex))
                println("Age: "+cursor.getInt(ageIndex))
            }

            cursor.close()


        }catch (e:Exception){
            // hatayi bastirir.
            e.printStackTrace()
        }
    }
}