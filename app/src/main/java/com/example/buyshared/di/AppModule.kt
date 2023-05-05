package com.example.buyshared.di

import android.content.Context
import androidx.room.Room
import com.example.buyshared.data.db.db
import com.example.buyshared.domain.repository.EventsRepository
import com.example.buyshared.domain.repository.EventsRepositoryImpl
import com.example.buyshared.domain.repository.ListsRepository
import com.example.buyshared.domain.repository.ListsRepositoryImpl
import com.example.buyshared.domain.repository.TasksRepository
import com.example.buyshared.domain.repository.TasksRepositoryImpl
import com.example.buyshared.domain.repository.UserRepository
import com.example.buyshared.domain.repository.UserReposityImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val DB_NAME = "buyshared"

    @Provides
    @Singleton
    fun provideDB(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, db::class.java, DB_NAME)
            .allowMainThreadQueries()
            .build()

    @Provides
    @Singleton
    fun provideUserRepository(db: db): UserRepository {
        return UserReposityImpl(db.getUserDao())
    }

    @Provides
    @Singleton
    fun provideEventRepository(db: db): EventsRepository {
        return EventsRepositoryImpl(db.getEventsDao())
    }
    @Provides
    @Singleton
    fun provideListRepository(db: db): ListsRepository {
        return ListsRepositoryImpl(db.getListsDao())
    }
    @Provides
    @Singleton
    fun provideTasksRepository(db: db): TasksRepository {
        return TasksRepositoryImpl(db.getTaskDao())
    }
}
