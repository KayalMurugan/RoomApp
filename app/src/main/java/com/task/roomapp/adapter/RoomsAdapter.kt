package com.task.roomapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task.roomapp.MainActivity
import com.task.roomapp.databinding.ListItemBinding
import com.task.roomapp.fragment.RoomsDialogFragment
import com.task.roomapp.model.Rooms
import com.task.roomapp.viewmodel.MainViewModel

class RoomsAdapter(
    private val roomsList: List<Rooms>,
    var viewModel: MainViewModel,
    var activity: MainActivity
) : RecyclerView.Adapter<RoomsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = roomsList[position]
        holder.bind(item)
    }

    override fun getItemCount() = roomsList.size
    inner class ViewHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Rooms) {
            binding.rooms = item
            binding.executePendingBindings()

            binding.roomsRLView.setOnClickListener {
                val bottomDialogFragment = RoomsDialogFragment()
                bottomDialogFragment.viewModel = viewModel
                viewModel.text.set(item.name)
                viewModel.isChecked.set(item.isLive)
                viewModel.id.set(item.id)
                viewModel.update.set(true)
                bottomDialogFragment.show(activity.supportFragmentManager, bottomDialogFragment.tag)
            }
        }
    }
}
