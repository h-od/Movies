package com.hod.themoviedb.network

import com.hod.themoviedb.detail.Detail
import com.hod.themoviedb.list.List
import io.reactivex.Observable
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface Movies {
    @GET("movie/550")
    fun fetchDetail(): Observable<Detail>

    @GET("discover/movie?sort_by=popularity.des")
    fun fetchList(): Observable<List>
}

class Service {
    private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(loggingInterceptor())
            .build()

    private fun loggingInterceptor(): Interceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC
        return logging
    }


    object interceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain?): Response {
            val request = chain!!.request()

            val requestUrl: HttpUrl =
                    request.url().newBuilder()
                            .addQueryParameter("api_key", "e08d99b7d5abe43e74086e1dd2b335d4")
                            .build()

            return chain.proceed(request.newBuilder().url(requestUrl).build())
        }
    }

    private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    val service: Movies = retrofit.create<Movies>(Movies::class.java)
}