package com.ngopidev.project.androidlatihan11_retrofit

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


/**
 *   created by Irfan Assidiq on 2019-06-20
 *   email : assidiq.irfan@gmail.com
 *
 *   secara url ditulis:
 *   https://en.wikipedia.org/w/api.php?action=query&format=json&list=search&srsearch=naruto
 **/
interface WikiApiServices {
    @GET("api.php")
    fun hitCountCheck(
        @Query("action") action : String,
        @Query("format") format : String,
        @Query("list") list : String,
        @Query("srsearch") srsearch: String
    ) : Observable<Model.Result>

    companion object {
        fun create()  : WikiApiServices{
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://en.wikipedia.org/w/")
                .build()

            return retrofit.create(
                WikiApiServices::class.java
            )
        }
    }
}