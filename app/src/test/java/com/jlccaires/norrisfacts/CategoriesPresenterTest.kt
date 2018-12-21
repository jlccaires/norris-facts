package com.jlccaires.norrisfacts

import com.jlccaires.norrisfacts.data.NorrisClient
import com.jlccaires.norrisfacts.data.NorrisService
import com.jlccaires.norrisfacts.presentation.category.CategoriesContract
import com.jlccaires.norrisfacts.presentation.category.CategoriesPresenter
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class CategoriesPresenterTest {

    @Mock
    lateinit var client: NorrisClient
    @Mock
    lateinit var service: NorrisService
    @Mock
    lateinit var view: CategoriesContract.View
    lateinit var presenter: CategoriesPresenter

    @Before
    fun setUp() {
        Mockito.`when`(client.api()).thenReturn(service)

        presenter = CategoriesPresenter()
        presenter.client = client
        presenter.attachView(view)

        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun testLoadCategories() {
        val itens = arrayListOf("a", "b", "c")
        Mockito.`when`(service.listCategories()).thenReturn(Observable.just(itens))

        presenter.loadCategories()

        val args = ArgumentCaptor.forClass(Boolean::class.java)
        Mockito.verify(view, Mockito.times(2)).setLoading(args.capture())
        Mockito.verify(view).setCategories(itens)

    }
}
