package com.example.buyshared.di

import android.app.Application
import android.util.Log
import com.example.buyshared.data.remote.HeaderInterceptor
import com.example.buyshared.data.remote.services.AddSolicitudApiClient
import com.example.buyshared.data.remote.services.ChangeInfoApi
import com.example.buyshared.data.remote.services.CompletTaskApi
import com.example.buyshared.data.remote.services.DelEventApi
import com.example.buyshared.data.remote.services.DelListApi
import com.example.buyshared.data.remote.services.DelTaskApiClient
import com.example.buyshared.data.remote.services.InsertEventApiClient
import com.example.buyshared.data.remote.services.InsertListApiClient
import com.example.buyshared.data.remote.services.InsertTaskApiClient
import com.example.buyshared.data.remote.services.LoadAvatarListApi
import com.example.buyshared.data.remote.services.LoadEventTasksApi
import com.example.buyshared.data.remote.services.LoadEventsApi
import com.example.buyshared.data.remote.services.LoadListsApi
import com.example.buyshared.data.remote.services.LoadListsTasksApi
import com.example.buyshared.data.remote.services.LoginApiClient
import com.example.buyshared.data.remote.services.RegisterApiClient
import com.example.buyshared.data.remote.services.UpdateAvatarApi
import com.example.buyshared.data.remote.services.UpdatePasswordApi
import com.example.buyshared.domain.repository.remote.AddSolicitudRetrofitRepository
import com.example.buyshared.domain.repository.remote.AddSolicitudRetrofitRepositoryImpl
import com.example.buyshared.domain.repository.remote.AddTaskRepositoryRetrofit
import com.example.buyshared.domain.repository.remote.AddTaskRepositoryRetrofitImpl
import com.example.buyshared.domain.repository.remote.ChangeInfoPersonalRetrofitRepository
import com.example.buyshared.domain.repository.remote.ChangeInfoPersonalRetrofitRepositoryImpl
import com.example.buyshared.domain.repository.remote.DelEventRepositoryRetrofit
import com.example.buyshared.domain.repository.remote.DelEventRepositoryRetrofitImpl
import com.example.buyshared.domain.repository.remote.DelListasRepositoryRetrofit
import com.example.buyshared.domain.repository.remote.DelListasRepositoryRetrofitImpl
import com.example.buyshared.domain.repository.remote.DelTaskRepositoryRetrofit
import com.example.buyshared.domain.repository.remote.DelTaskRepositoryRetrofitImpl
import com.example.buyshared.domain.repository.remote.EventsRepositoryRetrofit
import com.example.buyshared.domain.repository.remote.EventsRepositoryRetrofitImpl
import com.example.buyshared.domain.repository.remote.InsertEventRetrofitRepository
import com.example.buyshared.domain.repository.remote.InsertEventRetrofitRepositoryImpl
import com.example.buyshared.domain.repository.remote.InsertListRepository
import com.example.buyshared.domain.repository.remote.InsertListRepositoryImpl
import com.example.buyshared.domain.repository.remote.LoadAvatarListRepositoryRetrofit
import com.example.buyshared.domain.repository.remote.LoadAvatarListRepositoryRetrofitImpl
import com.example.buyshared.domain.repository.remote.LoadEventRepository
import com.example.buyshared.domain.repository.remote.LoadEventRepositoryImpl
import com.example.buyshared.domain.repository.remote.LoadListsRepository
import com.example.buyshared.domain.repository.remote.LoadListsRepositoryImpl
import com.example.buyshared.domain.repository.remote.LoadListsTasksRepository
import com.example.buyshared.domain.repository.remote.LoadListsTasksRepositoryImpl
import com.example.buyshared.domain.repository.remote.LoginRepository
import com.example.buyshared.domain.repository.remote.LoginRepositoryImpl
import com.example.buyshared.domain.repository.remote.RegisterRepository
import com.example.buyshared.domain.repository.remote.RegisterRepositoryImpl
import com.example.buyshared.domain.repository.remote.TaskRepositoryRetrofit
import com.example.buyshared.domain.repository.remote.TaskRepositoryRetrofitImpl
import com.example.buyshared.ui.Activity.TinyDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RetrofitHelper {

    @Provides
    @Singleton
    fun provideRetrofit(app: Application): Retrofit {
        var tinyDB = TinyDB(app)
        var server = tinyDB.getString("server")
        var token = tinyDB.getString("token")
        Log.v("buysharedLog","access token:"+ token)
        return Retrofit.Builder()
            .baseUrl(server + "v1/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun interceptorFun(token:String): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor { chain ->
            val newRequest: Request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            chain.proceed(newRequest)
        }.build()
    }


    @Provides
    @Singleton
    fun provideRetrofitv2(app: Application): Retrofit {
        var tinyDB = TinyDB(app)
        var server = tinyDB.getString("server")
        var token = tinyDB.getString("token")
        return Retrofit.Builder()
            .baseUrl(server + "v2/api/")
            .client(interceptorFun(token!!))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    @Singleton
    fun provideRetrofitv1(app: Application): Retrofit {
        var tinyDB = TinyDB(app)
        var server = tinyDB.getString("server")
        var token = tinyDB.getString("token")
        return Retrofit.Builder()
            .baseUrl(server + "v1/api/")
            .client(interceptorFun(token!!))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideGetHelperLogin(app: Application): LoginApiClient {
        var tinyDB = TinyDB(app)
        var server = tinyDB.getString("server")
        Log.v("buysharedLog","login")
        return Retrofit.Builder()
            .baseUrl(server + "v1/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LoginApiClient::class.java)
    }

    @Provides
    @Singleton
    fun provideGetHelperRegister(app: Application): RegisterApiClient {
        var tinyDB = TinyDB(app)
        var server = tinyDB.getString("server")
        Log.v("buysharedLog",server + "/api/")
        Log.v("buysharedLog","registro")
        return Retrofit.Builder()
            .baseUrl(server + "v1/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RegisterApiClient::class.java)
    }

    @Provides
    @Singleton
    fun provideLoadEvent(app: Application): LoadEventsApi {
        var tinyDB = TinyDB(app)
        var server = tinyDB.getString("server")
        var token = tinyDB.getString("token")
        Log.v("buysharedLog","registro:"+token)
        var client =  OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor(token!!))
            .build()
        return Retrofit.Builder()
            .baseUrl(server + "v1/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(LoadEventsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLoginRepository(api: LoginApiClient): LoginRepository {
        return LoginRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideRegisterRepository(api: RegisterApiClient): RegisterRepository {
        return RegisterRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideLoadEventRetrofic(app: Application): LoadEventRepository {
        var api = provideRetrofitv2(app).create(LoadEventsApi::class.java)
        return LoadEventRepositoryImpl(api)
    }
    @Provides
    @Singleton
    fun provideLoadListsRetrofic(app: Application): LoadListsRepository {
        var api = provideRetrofitv2(app).create(LoadListsApi::class.java)
        return LoadListsRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideInsertListsRetrofic(app: Application): InsertListRepository {
        var api = provideRetrofitv2(app).create(InsertListApiClient::class.java)
        return InsertListRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideInsertEventsRetrofic(app: Application): InsertEventRetrofitRepository {
        var api = provideRetrofitv1(app).create(InsertEventApiClient::class.java)
        return InsertEventRetrofitRepositoryImpl(api)
    }
    @Provides
    @Singleton
    fun provideLoadTaskListRetrofic(app: Application): LoadListsTasksRepository {
        var api = provideRetrofitv1(app).create(LoadListsTasksApi::class.java)
        return LoadListsTasksRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideEventsRetrofic(app: Application): EventsRepositoryRetrofit {
        var api = provideRetrofitv1(app).create(LoadEventTasksApi::class.java)
        return EventsRepositoryRetrofitImpl(api)
    }

    @Provides
    @Singleton
    fun provideCompletTaskApiRetrofic(app: Application): TaskRepositoryRetrofit {
        var api = provideRetrofitv1(app).create(CompletTaskApi::class.java)
        return TaskRepositoryRetrofitImpl(api)
    }

    @Provides
    @Singleton
    fun provideAvatarsListsRetrofic(app: Application): LoadAvatarListRepositoryRetrofit {
        var api = provideRetrofitv2(app).create(LoadAvatarListApi::class.java)
        return LoadAvatarListRepositoryRetrofitImpl(api)
    }

    @Provides
    @Singleton
    fun provideAddTaskRetrofic(app: Application): AddTaskRepositoryRetrofit {
        var api = provideRetrofitv1(app).create(InsertTaskApiClient::class.java)
        return AddTaskRepositoryRetrofitImpl(api)
    }
    @Provides
    @Singleton
    fun provideDelTaskRetrofic(app: Application): DelTaskRepositoryRetrofit {
        var api = provideRetrofitv1(app).create(DelTaskApiClient::class.java)
        return DelTaskRepositoryRetrofitImpl(api)
    }
    @Provides
    @Singleton
    fun provideAddSolicitud(app: Application): AddSolicitudRetrofitRepository {
        var api = provideRetrofitv1(app).create(AddSolicitudApiClient::class.java)
        return AddSolicitudRetrofitRepositoryImpl(api)
    }
    @Provides
    @Singleton
    fun provideDelList(app: Application): DelListasRepositoryRetrofit {
        var api = provideRetrofitv1(app).create(DelListApi::class.java)
        return DelListasRepositoryRetrofitImpl(api)
    }
    @Provides
    @Singleton
    fun provideDelEvents(app: Application): DelEventRepositoryRetrofit {
        var api = provideRetrofitv1(app).create(DelEventApi::class.java)
        return DelEventRepositoryRetrofitImpl(api)
    }
    @Provides
    @Singleton
    fun provideUpdateInfo(app: Application): ChangeInfoPersonalRetrofitRepository {
        var apiInfo = provideRetrofitv1(app).create(ChangeInfoApi::class.java)
        var apiPasswordApi = provideRetrofitv1(app).create(UpdatePasswordApi::class.java)
        var apiAvatar = provideRetrofitv1(app).create(UpdateAvatarApi::class.java)
        return ChangeInfoPersonalRetrofitRepositoryImpl(apiInfo,apiPasswordApi,apiAvatar)
    }
}