package com.application.e_greetings.others

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient
{
    companion object
    {

        var url="https://khushipatel3821.000webhostapp.com/Egreetings/"
        fun getapiclient(): Retrofit
        {
            var retrofit:Retrofit?=null

            if(retrofit==null)
            {
                retrofit=Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }


            return retrofit!!
        }
    }
}