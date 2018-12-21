package com.jlccaires.norrisfacts.presentation.base

import androidx.annotation.NonNull
import io.reactivex.disposables.CompositeDisposable
import org.androidannotations.annotations.EBean

@EBean
abstract class BasePresenter<VT : BaseContract.View?> :
    BaseContract.Presenter<VT> {

    private var mView: VT? = null
    lateinit var disposables: CompositeDisposable

    @NonNull
    fun getView(): VT {
        if (mView == null) {
            throw IllegalStateException("view not set")
        }
        return mView as VT
    }

    override fun attachView(view: VT) {
        mView = view
        disposables = CompositeDisposable()
    }

    override fun dispose() {
        disposables.dispose()
    }
}