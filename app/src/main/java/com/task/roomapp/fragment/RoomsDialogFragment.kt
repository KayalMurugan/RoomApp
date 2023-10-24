package com.task.roomapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.task.roomapp.R
import com.task.roomapp.databinding.RoomsDialogBinding
import com.task.roomapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RoomsDialogFragment : BottomSheetDialogFragment() {

    lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: RoomsDialogBinding = DataBindingUtil.inflate(
            inflater, R.layout.rooms_dialog, container, false
        )
        binding.root.background = context?.getDrawable(R.drawable.bottom_sheet_background)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel._dismissDialogEvent.value = false
        viewModel.dismissDialogEvent.observe(viewLifecycleOwner) { shouldDismiss ->
            if (shouldDismiss) {
                dismiss()
            }
        }

        return binding.root
    }

    override fun getTheme(): Int {
        return R.style.NoBackgroundDialogTheme
    }


}
