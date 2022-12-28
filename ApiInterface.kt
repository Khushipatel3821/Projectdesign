package com.application.e_greetings.others

import com.application.e_greetings.models.CategoryModel
import com.application.e_greetings.models.DashboardModel
import com.application.e_greetings.models.RegistrationModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
//https://khushipatel3821.000webhostapp.com/Egreetings/egreeting_view.php
//https://khushipatel3821.000webhostapp.com/Egreetings/login.php
//https://khushipatel3821.000webhostapp.com/Egreetings/register_view.php
interface ApiInterface
{
    @FormUrlEncoded
    @POST("registration.php")
    fun registerdetail(
        @Field("F_Name") F_Name: String?,
        @Field("L_Name") L_Name: String?,
        @Field("Mob_No") Mob_No: String?,
        @Field("Email") Email: String?,
        @Field("Pass") Pass: String?,

        ): Call<Void>

    @FormUrlEncoded
    @POST("login.php")
    fun logindata(  @Field("phone") mobile: String?,  @Field("pass") password: String?):Call<RegistrationModel>

    @get:GET("egreeting_view.php")
    val viewdata: Call<List<DashboardModel?>?>?

    @get:GET("diwali_view.php")
    val diwalidata: Call<List<CategoryModel?>?>?

}