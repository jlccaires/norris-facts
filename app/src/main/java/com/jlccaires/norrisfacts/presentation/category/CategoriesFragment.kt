package com.jlccaires.norrisfacts.presentation.category


import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.snackbar.Snackbar
import com.jlccaires.norrisfacts.R
import com.jlccaires.norrisfacts.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_categories.*
import javax.inject.Inject

class CategoriesFragment : BaseFragment<CategoriesContract.View, CategoriesContract.Presenter>(),
    CategoriesContract.View {

    override val layout = R.layout.fragment_categories

    @Inject
    override lateinit var mPresenter: CategoriesContract.Presenter
    protected lateinit var mAdapter: CategoriesAdapter

    override fun init() {
        component.inject(this)

        mAdapter = CategoriesAdapter()
        mAdapter.clickListener {
            findNavController().navigate(CategoriesFragmentDirections.actionCategoriesFragmentToJokeFragment(it))
        }
        rvCategories.adapter = mAdapter
        rvCategories.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        swipeRefresh.setColorSchemeResources(
            R.color.colorPrimary,
            R.color.colorAccent,
            R.color.colorPrimaryDark,
            R.color.colorAccent
        )
        swipeRefresh.setOnRefreshListener {
            mPresenter.loadCategories()
        }

        mPresenter.attachView(this)
        mPresenter.loadCategories()
    }

    override fun setLoading(loading: Boolean) {
        swipeRefresh.isRefreshing = loading
    }

    override fun setCategories(categories: List<String>) {
        mAdapter.data.addAll(categories)
        mAdapter.notifyDataSetChanged()
    }

    override fun showError() {
        Snackbar.make(view!!, R.string.error_message, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.try_again) { mPresenter.loadCategories() }
            .show()
    }
}
