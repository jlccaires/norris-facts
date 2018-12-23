package com.jlccaires.norrisfacts.data

import com.jlccaires.norrisfacts.BuildConfig
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NorrisClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.NORRIS_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()

    @Singleton
    @Provides
    fun api() = retrofit.create(NorrisService::class.java)
}