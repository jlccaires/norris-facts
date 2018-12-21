package com.jlccaires.norrisfacts.presentation.base

import androidx.fragment.app.Fragment
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EFragment

@EFragment
abstract class BaseFragment<V : BaseContract.View, T : BaseContract.Presenter<V>> : Fragment() {

    protected abstract var mPresenter: T

    @AfterViews
    fun afterViews() = init()

    abstract fun init()

    override fun onDestroyView() {
        mPresenter.dispose()
        super.onDestroyView()
    }
}