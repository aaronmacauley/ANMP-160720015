package com.example.anmp_week4.viewmodel

import android.app.Application
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.anmp_week4.model.Student
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DetailViewModel: ViewModel(){
    val studentLD = MutableLiveData<Student>()

//    fun fetch() {
//        val student1 = Student(
//            "16055",
//            "Nonie",
//            "1998/03/28",
//            "5718444778",
//            "http://dummyimage.com/75x100.jpg/cc0000/ffffff"
//        )
//        studentLD.value = student1
//    }
    fun fetch(studentId: String , context: Context) {
        val url = "http://adv.jitusolution.com/student.php?id=$studentId"
        val queue: RequestQueue = Volley.newRequestQueue(context)

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val student = Gson().fromJson(response.toString(), Student::class.java)
                studentLD.value = student
                Log.d(TAG, "Student Data: $student")
            },
            { error ->
                Log.e(TAG, "Error fetching student data: ${error.message}")
            }
        )

        queue.add(stringRequest)
    }
}