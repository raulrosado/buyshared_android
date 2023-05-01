package com.example.buyshared.di

import android.app.Application
import android.util.Log
import com.example.buyshared.data.remote.HeaderInterceptor
import com.example.buyshared.data.remote.services.InsertListApiClient
import com.example.buyshared.data.remote.services.LoadEventsApi
import com.example.buyshared.data.remote.services.LoadListsApi
import com.example.buyshared.data.remote.services.LoginApiClient
import com.example.buyshared.data.remote.services.RegisterApiClient
import com.example.buyshared.domain.repository.remote.InsertListRepository
import com.example.buyshared.domain.repository.remote.InsertListRepositoryImpl
import com.example.buyshared.domain.repository.remote.LoadEventRepository
import com.example.buyshared.domain.repository.remote.LoadEventRepositoryImpl
import com.example.buyshared.domain.repository.remote.LoadListsRepository
import com.example.buyshared.domain.repository.remote.LoadListsRepositoryImpl
import com.example.buyshared.domain.repository.remote.LoginRepository
import com.example.buyshared.domain.repository.remote.LoginRepositoryImpl
import com.example.buyshared.domain.repository.remote.RegisterRepository
import com.example.buyshared.domain.repository.remote.RegisterRepositoryImpl
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
    fun provideLoadEventRetrofit2(app: Application): Retrofit {
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
        var api = provideLoadEventRetrofit2(app).create(LoadEventsApi::class.java)
        return LoadEventRepositoryImpl(api)
    }
    @Provides
    @Singleton
    fun provideLoadListsRetrofic(app: Application): LoadListsRepository {
        var api = provideLoadEventRetrofit2(app).create(LoadListsApi::class.java)
        return LoadListsRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideInsertListsRetrofic(app: Application): InsertListRepository {
        var api = provideLoadEventRetrofit2(app).create(InsertListApiClient::class.java)
        return InsertListRepositoryImpl(api)
    }
//
//    @Provides
//    @Singleton
//    fun provideLoadChoferRetrofic(app: Application): LoadChoferesRepository {
//        var api = provideRetrofit(app).create(LoadChoferesApiClient::class.java)
//        return LoadChoferesRepositoryImpl(api)
//    }


//    @Provides
//    @Singleton
//    fun provideRegistroRepository(api: RegistroApiClient): RegistroRepository {
//        return RegistroRepositoryImpl(api)
//    }
//
//    @Provides
//    @Singleton
//    fun provideOlvidoRepository(api: OlvidoApiClient): OlvidoRepository {
//        return OlvidoRepositoryImpl(api)
//    }
//
//    @Provides
//    @Singleton
//    fun provideLoadPlacesRepository(api: GetPlacesApiClient): LoadPlacesRepository {
//        return LoadPlacesRepositoryImpl(api)
//    }
//
//    @Provides
//    @Singleton
//    fun provideAddPlacesRepository(api: ApiPlaceClient): SendPlaceToFavoritRepository {
//        return SendPlaceToFavoritRepositoryImpl(api)
//    }
}