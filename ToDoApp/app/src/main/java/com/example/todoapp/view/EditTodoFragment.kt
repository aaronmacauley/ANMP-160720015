package com.example.todoapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentEditTodoBinding
import com.example.todoapp.model.Todo
import com.example.todoapp.viewmodel.DetailTodoViewModel


class EditTodoFragment : Fragment(),RadioClick,TodoSaveChangesClick {
    private lateinit var viewModel: DetailTodoViewModel
    private lateinit var dataBinding: FragmentEditTodoBinding

    private lateinit var txtJudulTodo: TextView
    private lateinit var txtTitle: EditText
    private lateinit var txtNotes: EditText
    private lateinit var btnAdd: Button
    private lateinit var radioGroupPriority: RadioGroup
    private lateinit var radioHigh: RadioButton
    private lateinit var radioMedium: RadioButton
    private lateinit var radioLow: RadioButton

    override fun onRadioClick(v: View, priority: Int, obj: Todo) {
        obj.priority = priority
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataBinding.radioListener = this
        dataBinding.saveListener = this
        dataBinding.radioListener = this
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        txtJudulTodo = view.findViewById(R.id.txtJudulTodo)
        txtTitle = view.findViewById(R.id.txtTitle)
        txtNotes = view.findViewById(R.id.txtNotes)
        btnAdd = view.findViewById(R.id.btnAdd)
        radioGroupPriority = view.findViewById(R.id.radioGroupPriority)
        radioHigh = view.findViewById(R.id.radioHigh)
        radioMedium = view.findViewById(R.id.radioMedium)
        radioLow = view.findViewById(R.id.radioLow)

//        txtJudulTodo.text = "Edit Todo"
//        btnAdd.text = "Save Changes"

        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid

//        btnAdd.setOnClickListener {
//            val radio = view.findViewById<RadioButton>(radioGroupPriority.checkedRadioButtonId)
//
//
//            viewModel.update(
//                txtTitle.text.toString(), txtNotes.text.toString(),
//                radio.tag.toString().toInt(), uuid
//            )
//            Toast.makeText(view.context, "Todo updated", Toast.LENGTH_SHORT).show()
//            Navigation.findNavController(it).popBackStack()
//        }

        viewModel.fetch(uuid)


        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, {
            dataBinding.todo = it
//            txtTitle.setText(it.title)
//            txtNotes.setText(it.notes)
//
//            when (it.priority) {
//                1 -> radioLow.isChecked = true
//                2 -> radioMedium.isChecked = true
//                else -> radioHigh.isChecked = true
//            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        dataBinding =DataBindingUtil.inflate<FragmentEditTodoBinding>(inflater,R.layout.fragment_edit_todo,container,false)
        return dataBinding.root
//        return inflater.inflate(R.layout.fragment_create_todo, container, false)
    }

    override fun onTodoSaveChangesClick(v: View, obj: Todo) {
        Log.d("cobacek",obj.toString())
        viewModel.update(obj.title, obj.notes, obj.priority, obj.uuid)
        Toast.makeText(v.context, "Todo Updated", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(v).popBackStack()
    }
}
