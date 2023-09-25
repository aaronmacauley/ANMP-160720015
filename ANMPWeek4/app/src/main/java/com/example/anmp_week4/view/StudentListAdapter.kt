package com.example.anmp_week4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.anmp_week4.R
import com.example.anmp_week4.model.Student

class StudentListAdapter(val studentList:ArrayList<Student>)
    :RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(){
    class StudentViewHolder(view:View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.student_list_item, parent, false)
        return StudentViewHolder(view)
    }

    override fun getItemCount(): Int {
       return studentList.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val txtID = holder.itemView.findViewById<TextView>(R.id.txtID);
        val txtName = holder.itemView.findViewById<TextView>(R.id.txtName);
        val btnDetail = holder.itemView.findViewById<Button>(R.id.btnDetail)
        txtID.text=studentList[position].id
        txtName.text=studentList[position].name
        btnDetail.setOnClickListener{
            val action = StudentListFragmentDirections.ActionStudentDetail()
            Navigation.findNavController(it).navigate(action)
        }
    }
    fun updateStudentList(newStudentList: ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }
}