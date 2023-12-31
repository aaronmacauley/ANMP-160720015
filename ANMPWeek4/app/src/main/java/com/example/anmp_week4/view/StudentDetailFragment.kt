package com.example.anmp_week4.view

import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.anmp_week4.R
import com.example.anmp_week4.databinding.FragmentStudentDetailBinding
import com.example.anmp_week4.model.Student
import com.example.anmp_week4.viewmodel.DetailViewModel
import com.example.anmp_week4.viewmodel.ListViewModel
import com.example.anmp_week4.viewmodel.NarutosViewModel
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment(), ButtonDetailClickListener  {
    private lateinit var viewModel:DetailViewModel
    var studentId:String?=null
    private lateinit var binding: FragmentStudentDetailBinding
//    private val studentDetailAdapter = StudentDetailAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_student_detail, container, false)
        binding = FragmentStudentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments!=null){
            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
//            val txtID = binding.txtID
//            val txtName = binding.txtName
//            val txtBod = binding.txtBod
//            val txtPhone = binding.txtPhone
//            val btnUpdate = binding.btnUpdate


//            val txtID = view.findViewById<EditText>(R.id.txtID)
//            val studentId = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentId
//            val txtName = view.findViewById<EditText>(R.id.txtName)
//            val txtBod = view.findViewById<EditText>(R.id.txtBod)
//            val txtPhone = view.findViewById<EditText>(R.id.txtPhone)
//            val btnUpdate = view?.findViewById<Button>(R.id.btnUpdate)
//            val imgDetailPhoto = view.findViewById<ImageView>(R.id.imgViewDetail)


            arguments?.let {
                studentId = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentId
            }

            studentId?.let {
                viewModel.fetch(it, requireContext())
            }

//            viewModel.studentLD.observe(viewLifecycleOwner, Observer {
//                var student = it
//                txtID.setText(student.id)
//                txtName.setText(student.name)
//                txtBod.setText(student.dob)
//                txtPhone.setText(student.phone)
//                val picasso = Picasso.Builder(view.context)
//                picasso.listener { picasso, uri, exception ->
//                    exception.printStackTrace()
//                }
//                picasso.build().load(student.photoUrl).into(imgDetailPhoto)
//
//            })

            viewModel.studentLD.observe(viewLifecycleOwner, Observer {
                val student = it
                binding.student = student
            })

//            btnUpdate?.setOnClickListener {
//                Observable.timer(5, TimeUnit.SECONDS)
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe {
//                        Log.d("Messages", "five seconds")
//                        MainActivity.showNotification(
//                            "new Notification",
//                            "A new notification created",
//                            R.drawable.twotone_save_24
//                        )
//                    }
//
//            }

        }

    }
    override fun onButtonDetailClick(v: View) {
        Observable.timer(5, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        Log.d("Messages", "five seconds")
                        MainActivity.showNotification(
                            "new Notification",
                            "A new notification created",
                            R.drawable.twotone_save_24
                        )
                    }
    }
}