package com.jlccaires.norrisfacts.di

import com.jlccaires.norrisfacts.data.NorrisService
import com.jlccaires.norrisfacts.presentation.category.CategoriesContract
import com.jlccaires.norrisfacts.presentation.category.CategoriesPresenter
import com.jlccaires.norrisfacts.presentation.joke.JokeContract
import com.jlccaires.norrisfacts.presentation.joke.JokePresenter
import dagger.Module
import dagger.Provides

@Module
class PresenterProvider {

    @Provides
    fun provideCategoryPresenter(service: NorrisService): CategoriesContract.Presenter = CategoriesPresenter(service)

    @Provides
    fun provideJokePresenter(service: NorrisService): JokeContract.Presenter = JokePresenter(service)

}