package com.register.festapp.di

import android.app.Application
import com.register.festapp.data.database.AppDatabase
import com.register.festapp.data.database.ShopDao
import com.register.festapp.data.network.ApiFactory
import com.register.festapp.data.network.ApiService
import com.register.festapp.data.repository.ShopRepositoryImpl
import com.register.festapp.domain.repository.ShopRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindRepository(impl: ShopRepositoryImpl): ShopRepository

    companion object {

        @Provides
        @ApplicationScope
        fun provideShopDao(
            application: Application
        ): ShopDao {
            return AppDatabase.getInstance(application).shopInfoDao()
        }

        @Provides
        @ApplicationScope
        fun provideDatabase(
            application: Application
        ): AppDatabase {
            return AppDatabase.getInstance(application)
        }

        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }

    }

}