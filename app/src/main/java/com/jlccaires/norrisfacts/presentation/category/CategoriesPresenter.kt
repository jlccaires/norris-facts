package com.jlccaires.norrisfacts.presentation.category

import androidx.annotation.VisibleForTesting
import com.jlccaires.norrisfacts.data.NorrisClient
import com.jlccaires.norrisfacts.presentation.addTo
import com.jlccaires.norrisfacts.presentation.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.EBean

@EBean
class CategoriesPresenter : BasePresenter<CategoriesContract.View>(), CategoriesContract.Presenter {

    @Bean
    @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    lateinit var client: NorrisClient

    override fun loadCategories() {
        getView().setLoading(true)
        client.api().listCategories()
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