package com.viper.databasework.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.viper.databasework.R
import com.viper.databasework.databinding.FragmentSortBinding
import com.viper.databasework.viewmodel.SortViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * created by viper on 2021/6/8
 * desc
 */
class SortDialogFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentSortBinding
    private val sortViewModel by sharedViewModel<SortViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSortBinding.inflate(inflater, container, false)
        context ?: return binding.root

        onInitView()
        onInitAction()

        return binding.root
    }

    private fun onInitView() {
        binding.sortKeyName.isChecked = true
        binding.sortKeyAsc.isChecked = true
    }

    private fun onInitAction() {
        binding.sortKey.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.sort_key_name -> sortViewModel.sortKey = "name"
                R.id.sort_key_brand -> sortViewModel.sortKey = "brand"
                R.id.sort_key_price -> sortViewModel.sortKey = "price"
                R.id.sort_key_store -> sortViewModel.sortKey = "store"
                R.id.sort_key_type -> sortViewModel.sortKey = "type"
            }
        }
        binding.sortOrder.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.sort_key_asc -> sortViewModel.sortKey = "asc"
                R.id.sort_key_des -> sortViewModel.sortKey = "des"
            }
        }
        binding.sortBtnConfirm.setOnClickListener {
            this.dismiss()
            sortViewModel.sort()
        }
    }
}