package com.application.e_greetings.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.application.e_greetings.R
import com.application.e_greetings.activities.CategoryActivity
import com.application.e_greetings.models.DashboardModel
import com.application.e_greetings.others.ApiInterface
import com.squareup.picasso.Picasso

class MyAdpter (context: Context, dataList: List<DashboardModel>) :
    RecyclerView.Adapter<MyAdpter.Myview>()
    {
        var context: Context
        var dataList: List<DashboardModel>
        var api: ApiInterface? = null

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myview {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.design_dashboard, parent, false)
            return Myview(view)
        }

        override fun onBindViewHolder(holder: Myview, @SuppressLint("RecyclerView") position: Int) {
            holder.t2.setText(dataList[position].name)
            Picasso.get().load(dataList[position].image).into(holder.img)
            holder.img.setOnClickListener()
            {
                var i = Intent(context, CategoryActivity::class.java)
                i.putExtra("MyPos", position)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(i)
            }


        }

        override fun getItemCount(): Int {
            return dataList.size
        }

        inner class Myview(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var img: ImageView
            var t2: TextView


            init {
                img = itemView.findViewById(R.id.img)
                t2 = itemView.findViewById(R.id.txt)

            }
        }

        init {
            this.context = context
            this.dataList = dataList
        }

    }
