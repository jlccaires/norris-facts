package com.jlccaires.norrisfacts.presentation.joke

import android.content.Intent
import android.net.Uri
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.jlccaires.norrisfacts.R
import com.jlccaires.norrisfacts.presentation.NorrisAnimations
import com.jlccaires.norrisfacts.presentation.base.BaseFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_joke.*
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.EFragment


@EFragment(R.layout.fragment_joke)
class JokeFragment : BaseFragment<JokeContract.View, JokeContract.Presenter>(), JokeContract.View {

    @Bean(JokePresenter::class)
    override lateinit var mPresenter: JokeContract.Presenter

    override fun init() {
        mPresenter.attachView(this)
        mPresenter.setCategory(JokeFragment_Args.fromBundle(arguments).category)
        cardView.setOnClickListener {
            mPresenter.jokeClicked()
        }
        mPresenter.loadJoke()
    }

    override fun setLoading(loading: Boolean) {
    }

    override fun setJoke(joke: String?) {
        tvText.text = joke

        ibShare.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, joke)
                type = "text/plain"
            }
            startActivity(sendIntent)
        }

        cardView.visibility = View.VISIBLE
        NorrisAnimations.jokeInAnimation(cardView)
    }

    override fun setIcon(icon: String?) {
        Picasso.get().load(icon).into(ivIcon)
    }

    override fun setUrl(joke: String?) {
        ibUrl.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(joke))
            startActivity(intent)
        }
    }

    override fun hideJoke() {
        NorrisAnimations.jokeOutAnimation(cardView) {
            cardView.visibility = View.INVISIBLE
            mPresenter.loadJoke()
        }
    }

    override fun showError() {
        Snackbar.make(view!!, R.string.error_message, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.try_again) { mPresenter.loadJoke() }
            .show()
    }
}
