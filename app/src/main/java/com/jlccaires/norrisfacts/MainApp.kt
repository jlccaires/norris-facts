package com.jlccaires.norrisfacts

import android.app.Application
import com.jlccaires.norrisfacts.di.AppComponent
import com.jlccaires.norrisfacts.di.DaggerAppComponent

class MainApp : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .build()
    }
}