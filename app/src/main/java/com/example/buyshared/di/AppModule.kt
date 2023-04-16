package com.example.buyshared.di

import android.app.Application
import androidx.room.Room
import com.example.buyshared.data.db.db
import com.example.buyshared.domain.repository.UserRepository
import com.example.buyshared.domain.repository.UserReposityImpl
import com.example.buyshared.domain.repository.remote.LoginRepository
import com.example.buyshared.domain.repository.remote.LoginRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDB(app: Application): db {
        return Room.databaseBuilder(
            app,
            db::class.java,
            "buyshared"
        )
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideUserRepository(db:db) : UserRepository {
        return UserReposityImpl(db.userDao)
    }
}