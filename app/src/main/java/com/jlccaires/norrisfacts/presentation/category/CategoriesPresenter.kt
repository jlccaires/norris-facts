package com.jlccaires.norrisfacts.presentation.category

import com.jlccaires.norrisfacts.data.NorrisService
import com.jlccaires.norrisfacts.presentation.addTo
import com.jlccaires.norrisfacts.presentation.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class CategoriesPresenter : BasePresenter<CategoriesContract.View>, CategoriesContract.Presenter {

    private val service: NorrisService

    @Inject
    constructor(service: NorrisService) {
        this.service = service
    }

    override fun loadCategories() {
        getView().setLoading(true)
        service.listCategories()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { list ->
                    getView().setCategories(list)
                    getView().setLoading(false)
                },
                {
                    getView().showError()
                }
            ).addTo(disposables)
    }

}