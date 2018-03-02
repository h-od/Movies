package com.hod.themoviedb.network

import com.hod.themoviedb.list.List
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface Detail {
//    @GET("3/movie/550?api_key=ba7c44cc6a07d2af0caaaea05905ec43")
//    fun get(): Observable<List>
    @GET("3/discover/movie?sort_by=popularity.des?api_key=ba7c44cc6a07d2af0caaaea05905ec43")
    fun get(): Observable<List>
}

class Service {
    private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val service: Detail = retrofit.create<Detail>(Detail::class.java)
}