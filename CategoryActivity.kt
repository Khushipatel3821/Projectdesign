package com.application.e_greetings.activities

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.e_greetings.R
import com.application.e_greetings.adapters.Myadpter2
import com.application.e_greetings.databinding.ActivityDashboardBinding
import com.application.e_greetings.models.CategoryModel
import com.application.e_greetings.others.ApiClient
import com.application.e_greetings.others.ApiInterface
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryActivity : AppCompatActivity()
{
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: ActivityDashboardBinding
    lateinit var recyclerView: RecyclerView
    lateinit var list: MutableList<CategoryModel>
    lateinit var apiinterface: ApiInterface
    var call: Call<List<CategoryModel?>?>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sharedPreferences = getSharedPreferences("user_session", Context.MODE_PRIVATE)
        recyclerView = findViewById(R.id.recycler)
        list = ArrayList<CategoryModel>()

        var i=intent
        var pos= i.getIntExtra("MyPos",100)

        // Toast.makeText(applicationContext,""+pos,Toast.LENGTH_LONG).show()

        val rl: RecyclerView.LayoutManager = GridLayoutManager(applicationContext, 2)
        recyclerView.layoutManager = rl
        apiinterface = ApiClient.getapiclient().create(ApiInterface::class.java)

        if(pos==0)
        {
            call = apiinterface!!.diwalidata
        }


        call!!.enqueue(object : Call<List<CategoryModel?>>, Callback<List<CategoryModel?>?> {
            /*  override fun onResponse(call: Call<List<Model?>>, response: Response<List<Model?>>) {

              }

              override fun onFailure(call: Call<List<Model?>>, t: Throwable) {}*/
            override fun onResponse(call: Call<List<CategoryModel?>?>, response: Response<List<CategoryModel?>?>) {
                list = response.body() as MutableList<CategoryModel>
                val customAdapter = Myadpter2(applicationContext, list!!)
                recyclerView.adapter = customAdapter
            }

            override fun onFailure(call: Call<List<CategoryModel?>?>, t: Throwable) {
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
            }

            override fun clone(): Call<List<CategoryModel?>> {
                TODO("Not yet implemented")
            }

            override fun execute(): Response<List<CategoryModel?>> {
                TODO("Not yet implemented")
            }

            override fun enqueue(callback: Callback<List<CategoryModel?>>) {
                TODO("Not yet implemented")
            }

            override fun isExecuted(): Boolean {
                TODO("Not yet implemented")
            }

            override fun cancel() {
                TODO("Not yet implemented")
            }

            override fun isCanceled(): Boolean {
                TODO("Not yet implemented")
            }

            override fun request(): Request {
                TODO("Not yet implemented")
            }


        })


    }
}