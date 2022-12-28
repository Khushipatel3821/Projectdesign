package com.application.e_greetings.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.application.e_greetings.R
import com.application.e_greetings.databinding.ActivityLoginBinding
import com.application.e_greetings.models.RegistrationModel
import com.application.e_greetings.others.ApiClient
import com.application.e_greetings.others.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    lateinit var sharedPreferences: SharedPreferences
    lateinit var apiinterface: ApiInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding: ActivityLoginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("user_session", Context.MODE_PRIVATE)



        if (sharedPreferences.getBoolean(
                "user_session",
                false
            ) && !sharedPreferences.getString("m1", "")!!.isEmpty()
        ) {
            val i = Intent(this, DashboardActivity::class.java)
            startActivity(i)
            finish()
        }
        binding.login.setOnClickListener()
        {
            var mobile = binding.edt1.text.toString()
            var pass = binding.edt2.text.toString()

            apiinterface = ApiClient.getapiclient().create(ApiInterface::class.java)
            var call: Call<RegistrationModel> = apiinterface.logindata(mobile, pass)
            call.enqueue(object : Callback<RegistrationModel> {
                override fun onResponse(
                    call: Call<RegistrationModel>,
                    response: Response<RegistrationModel>,
                ) {
                    sharedPreferences.edit().putString("mob", mobile).commit()

                    sharedPreferences.edit().putBoolean("user_session", true).commit()
                    Toast.makeText(applicationContext, "Success", Toast.LENGTH_LONG).show()

                    startActivity(Intent(applicationContext, DashboardActivity::class.java))
                }

                override fun onFailure(call: Call<RegistrationModel>, t: Throwable) {
                    Toast.makeText(applicationContext, "Fail", Toast.LENGTH_LONG).show()
                }
            })

        }

        binding.signup.setOnClickListener {

            startActivity(Intent(this, RegistrationActivity::class.java))

        }

    }
}
