package com.example.buyshared.di

import android.app.Application
import android.util.Log
import com.example.buyshared.data.remote.HeaderInterceptor
import com.example.buyshared.data.remote.services.LoginApiClient
import com.example.buyshared.domain.repository.remote.LoginRepository
import com.example.buyshared.domain.repository.remote.LoginRepositoryImpl
import com.example.buyshared.ui.Activity.TinyDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
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
        var client =  OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor(token!!))
            .build()
        return Retrofit.Builder()
            .baseUrl(server + "v1/api/")
            .client(client)
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
    fun provideLoginRepository(api: LoginApiClient): LoginRepository {
        return LoginRepositoryImpl(api)
    }

//    @Provides
//    @Singleton
//    fun provideGetHelperRegistro(app: Application): RegistroApiClient {
//        var tinyDB = TinyDB(app)
//        var server = tinyDB.getString("server")
//        Log.v("muevetyLog",server + "/api/")
//        Log.v("muevetyLog","registro")
//        return Retrofit.Builder()
//            .baseUrl(server + "/api/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(RegistroApiClient::class.java)
//    }



//    @Provides
//    @Singleton
//    fun provideDelRetrofic(app: Application): DelPlaceRepository {
//        var api = provideRetrofit(app).create(DelPlaceClient::class.java)
//        return DelPlaceRepositoryImpl(api)
//    }
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