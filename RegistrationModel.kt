package com.application.e_greetings.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RegistrationModel
{
    @Expose
    @SerializedName("F_Name")
    var fname=""

    @Expose
    @SerializedName("L_Name")
    var lname=""

    @Expose
    @SerializedName("Mob_No")
    var mobile=""

    @Expose
    @SerializedName("Email")
    var email=""

    @Expose
    @SerializedName("Pass")
    var password=""
}