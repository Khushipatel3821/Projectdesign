package com.application.e_greetings.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.e_greetings.R
import com.application.e_greetings.adapters.MyAdpter
import com.application.e_greetings.databinding.ActivityDashboardBinding
import com.application.e_greetings.models.DashboardModel
import com.application.e_greetings.others.ApiClient
import com.application.e_greetings.others.ApiInterface
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

lateinit var sharedPreferences: SharedPreferences
private lateinit var binding: ActivityDashboardBinding
lateinit var recyclerView: RecyclerView
lateinit var list: MutableList<DashboardModel>
lateinit var apiinterface: ApiInterface


class DashboardActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sharedPreferences = getSharedPreferences("user_session", Context.MODE_PRIVATE)

        Toast.makeText(applicationContext,
            "Welcome " + sharedPreferences.getString("mob", "error"),
            Toast.LENGTH_LONG).show()

        recyclerView = findViewById(R.id.recycler)
        list = ArrayList<DashboardModel>()

        val rl: RecyclerView.LayoutManager = GridLayoutManager(applicationContext, 2)
        recyclerView.layoutManager = rl

        apiinterface = ApiClient.getapiclient().create(ApiInterface::class.java)

        val call = apiinterface!!.viewdata

        call!!.enqueue(object : Call<List<DashboardModel?>>, Callback<List<DashboardModel?>?> {
            /*  override fun onResponse(call: Call<List<Model?>>, response: Response<List<Model?>>) {

              }

              override fun onFailure(call: Call<List<Model?>>, t: Throwable) {}*/
            override fun onResponse(call: Call<List<DashboardModel?>?>, response: Response<List<DashboardModel?>?>) {
                list = response.body() as MutableList<DashboardModel>
                val customAdapter = MyAdpter(applicationContext, list!!)
                recyclerView.adapter = customAdapter
            }

            override fun onFailure(call: Call<List<DashboardModel?>?>, t: Throwable) {
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_LONG).show()
            }

            override fun clone(): Call<List<DashboardModel?>> {
                TODO("Not yet implemented")
            }

            override fun execute(): Response<List<DashboardModel?>> {
                TODO("Not yet implemented")
            }

            override fun enqueue(callback: Callback<List<DashboardModel?>>) {
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean
    {
        menuInflater.inflate(R.menu.option,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when(item.itemId)
        {
            R.id.logout->
            {
                sharedPreferences.edit().clear().commit()
                startActivity(Intent(this, LoginActivity::class.java))
            }

        }


        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {

        finishAffinity()
    }

}