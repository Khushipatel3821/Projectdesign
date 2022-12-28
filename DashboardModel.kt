package com.application.e_greetings.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DashboardModel
{
    @Expose
    @SerializedName("Name")
    var name=""

    @Expose
    @SerializedName("Image")
    var image=""

}