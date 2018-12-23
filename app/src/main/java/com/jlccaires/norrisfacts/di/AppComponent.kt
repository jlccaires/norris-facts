package com.jlccaires.norrisfacts.di

import com.jlccaires.norrisfacts.data.NorrisClient
import com.jlccaires.norrisfacts.presentation.category.CategoriesContract
import com.jlccaires.norrisfacts.presentation.category.CategoriesFragment
import com.jlccaires.norrisfacts.presentation.joke.JokeContract
import com.jlccaires.norrisfacts.presentation.joke.JokeFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [PresenterProvider::class, NorrisClient::class])
interface AppComponent {
    fun inject(fragment: CategoriesFragment)
    fun inject(fragment: JokeFragment)
    fun inject(presenter: CategoriesContract.Presenter)
    fun inject(presenter: JokeContract.Presenter)
}