package com.jlccaires.norrisfacts.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jlccaires.norrisfacts.MainApp
import com.jlccaires.norrisfacts.di.AppComponent

abstract class BaseFragment<V : BaseContract.View, T : BaseContract.Presenter<V>> : Fragment() {

    protected abstract val layout: Int
    protected abstract var mPresenter: T
    protected val component: AppComponent get() = (activity?.application as MainApp).component

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = init()

    abstract fun init()

    override fun onDestroyView() {
        mPresenter.dispose()
        super.onDestroyView()
    }
}