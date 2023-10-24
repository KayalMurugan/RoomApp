package com.task.roomapp

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.task.roomapp.adapter.RoomsAdapter
import com.task.roomapp.databinding.ActivityMainBinding
import com.task.roomapp.fragment.RoomsDialogFragment
import com.task.roomapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    var binding: ActivityMainBinding? = null
    lateinit var activity: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity = this
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        init()


    }

    fun init() {
        viewModel.getAllRooms.observe(this) { roomsList ->
            roomsList?.let {
                val adapter = RoomsAdapter(it, viewModel, activity as MainActivity)
                binding?.roomsRecycler?.adapter = adapter
            }
        }
        viewModel.showToastMessage.observe(this) { message ->
            message?.let {
                Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
            }
        }
        binding?.addButton?.setOnClickListener {
            create()
        }
    }

    fun create() {
        viewModel.text.set("")
        viewModel.isChecked.set(false)
        viewModel.id.set(0)
        viewModel.update.set(false)
        val bottomDialogFragment = RoomsDialogFragment()
        bottomDialogFragment.viewModel = viewModel
        bottomDialogFragment.show(supportFragmentManager, bottomDialogFragment.tag)
    }
}


