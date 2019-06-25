package com.ngopidev.project.androidlatihan11_retrofit

import com.google.gson.annotations.SerializedName
import retrofit2.http.Query


/**
 *   created by Irfan Assidiq on 2019-06-20
 *   email : assidiq.irfan@gmail.com
 **/
object Model {
    data class Result(@SerializedName("query") val query : Query)
    data class Query(@SerializedName("searchinfo") val searchInfo : SearchInfo)
    data class SearchInfo( @SerializedName("totalhits") val totahits : Int)
}