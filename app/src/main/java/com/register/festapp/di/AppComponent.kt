package com.register.festapp.di

import android.app.Application
import com.register.festapp.presentation.MainActivity
import com.register.festapp.presentation.ShoppingApp
import com.register.festapp.presentation.screens.CatalogFragment
import com.register.festapp.presentation.screens.FavouritesFragment
import com.register.festapp.presentation.screens.ProfileFragment
import com.register.festapp.presentation.screens.ShopItemInfoFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: CatalogFragment)

    fun inject(fragment: ShopItemInfoFragment)

    fun inject(fragment: ProfileFragment)

    fun inject(fragment: FavouritesFragment)

    fun inject(application: ShoppingApp)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }

}