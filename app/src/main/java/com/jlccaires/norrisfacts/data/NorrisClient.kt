package com.jlccaires.norrisfacts.data

import com.jlccaires.norrisfacts.BuildConfig
import io.reactivex.schedulers.Schedulers
import org.androidannotations.annotations.EBean
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@EBean(scope = EBean.Scope.Singleton)
class NorrisClient {

    private val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.NORRIS_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()

    fun api() = retrofit.create(NorrisService::class.java)
}