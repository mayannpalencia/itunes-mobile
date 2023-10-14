package com.mayann.itunesmobile.features.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import com.mayann.itunesmobile.features.base.hilt.HiltActivityEntryPoint


/**
 * @author May Ann Palencia on 10/13/2023
 * @version 1.0.0
 * @use An abstract class acting as a wrapper to eliminate some boiler codes from our activities.
 * @desc Mobile Developer
 * @since 1.0
 */
abstract class BaseActivity<VB : ViewBinding>(
    private val setupViewBinding: (LayoutInflater) -> VB
) : HiltActivityEntryPoint() {

    lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setupViewBinding(layoutInflater)
        setContentView(binding.root)

        setUpObserver()
        setUpView()
        addEventListener()
        loadData()
    }

    open fun setUpObserver() {}
    open fun loadData() {}
    open fun setUpView() {}
    open fun addEventListener() {}
}