package com.example.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import org.koin.androidx.viewmodel.ext.android.viewModelForClass
import kotlin.reflect.KClass

abstract class BaseFragment<VB: ViewBinding, VM: ViewModel>(
    private val inflateBinding: (
    inflate: LayoutInflater,
    container: ViewGroup?,
    attachToParent: Boolean,
    )-> VB,
clazz: KClass<VM>
): Fragment() {
    private var _binding: VB? = null
    protected val binding get() = requireNotNull(_binding)
    protected val viewModel: VM by viewModelForClass(clazz)

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflateBinding.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        initialize()
    }

//    abstract fun showPlaceholder()
//    abstract fun showProgress()
//    abstract fun showContent()

    abstract fun initialize()
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    open fun setListeners() = Unit
}