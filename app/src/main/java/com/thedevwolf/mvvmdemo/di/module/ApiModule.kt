package com.thedevwolf.mvvmdemo.di.module

import android.util.Log
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import com.thedevwolf.mvvmdemo.BuildConfig
import com.thedevwolf.mvvmdemo.data.network.Api
import com.thedevwolf.mvvmdemo.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@Suppress("unused")

object ApiModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun providePostApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(client: OkHttpClient,
                                          gsonConverterFactory: GsonConverterFactory,
                                          rxJava2CallAdapterFactory: RxJava2CallAdapterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .client(client)
            .build()
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(httpLoggingInterceptor)
        }
        builder.connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS).build()
        return builder.build()
    }
    @Provides
    @Reusable
    @JvmStatic
    internal fun providerGsonConverter():GsonConverterFactory{
        return GsonConverterFactory.create()
    }
    @Provides
    @Reusable
    @JvmStatic
    internal fun providerRxJava2Adapter():RxJava2CallAdapterFactory{
        return RxJava2CallAdapterFactory.create()
    }


    @Provides
    @Reusable
    @JvmStatic
    internal fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Log.d(javaClass.simpleName, message) })
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return httpLoggingInterceptor
    }
}