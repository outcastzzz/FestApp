package com.register.festapp.presentation

import android.app.Application
import com.register.festapp.di.AppComponent
import com.register.festapp.di.DaggerAppComponent

class ShoppingApp: Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent.factory()
            .create(this)
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }

}