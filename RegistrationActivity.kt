package com.application.e_greetings.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.application.e_greetings.databinding.ActivityRegistrationBinding
import com.application.e_greetings.others.ApiClient
import com.application.e_greetings.others.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class  RegistrationActivity : AppCompatActivity()
{

    private lateinit var binding :  ActivityRegistrationBinding
    lateinit var apiinterface: ApiInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding: ActivityRegistrationBinding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signin.setOnClickListener {

            startActivity(Intent(this,LoginActivity::class.java))
        }
        binding.register.setOnClickListener {

            var fname = binding.fname.text.toString()
            var lname = binding.lname.text.toString()
            var mobno = binding.mobno.text.toString()
            var email = binding.email.text.toString()
            var pass = binding.pass.text.toString()
            var cpass = binding.conpass.text.toString()

            if(fname == "" || lname == "" || mobno == "" || email == "" || pass == "")
            {
                Toast.makeText(applicationContext,"Please Enter The Appropriate Value",Toast.LENGTH_LONG).show()
            }
            if(!mobno.equals("(0/91)?[6-9][0-9]{9}") )
            {
                Toast.makeText(applicationContext,"Enter valid mob-no with country code",Toast.LENGTH_LONG).show()
            }
            if(!email.equals("^[A-Za-z0-9+_.-]+@(.+)"))
            {
                Toast.makeText(applicationContext, "Enter valid email address",Toast.LENGTH_LONG).show()
            }
            if(!pass.equals("^(?=.*)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}"))
            {
                Toast.makeText(applicationContext, "Enter valid password",Toast.LENGTH_LONG).show()
            }
            if(pass.equals(cpass))
            {
                apiinterface = ApiClient.getapiclient().create(ApiInterface::class.java)
                var registercall: Call<Void> = apiinterface.registerdetail(fname, lname, mobno, email, pass)
                registercall.enqueue(object : Callback<Void?>
                {
                    override fun onResponse(call: Call<Void?>, response: Response<Void?>, )
                    {



                    }

                    override fun onFailure(call: Call<Void?>, t: Throwable)
                    {


                        //Toast.makeText(applicationContext, "" + t, Toast.LENGTH_LONG).show()
                        Log.d("XyzError",t.message.toString())
                    }
                })
                startActivity(Intent(this, LoginActivity::class.java))
            }
            else
            {
                Toast.makeText(applicationContext,"Your Password and confirm password are not same",Toast.LENGTH_LONG).show()
            }
        }
    }
}