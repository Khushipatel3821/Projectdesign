package com.application.e_greetings.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.application.e_greetings.R
import com.application.e_greetings.activities.FullScreenActivity
import com.application.e_greetings.models.CategoryModel
import com.application.e_greetings.others.ApiInterface
import com.squareup.picasso.Picasso

class Myadpter2(context: Context, dataList: List<CategoryModel>) : RecyclerView.Adapter<Myadpter2.Myview>() {
    var context: Context
    var dataList: List<CategoryModel>
    var api: ApiInterface? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myview
    {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.design_category, parent, false)
        return Myview(view)
    }

    override fun onBindViewHolder(holder: Myview, @SuppressLint("RecyclerView") position: Int)
    {

        Picasso.get().load(dataList[position].image).into(holder.img)
        holder.img.setOnClickListener()
        {
            var i =Intent(context,  FullScreenActivity::class.java)
            i.putExtra("image",dataList[position].image)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(i)
        }


    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class Myview(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img: ImageView



        init {
            img = itemView.findViewById(R.id.img)


        }
    }

    init {
        this.context = context
        this.dataList = dataList
    }
}