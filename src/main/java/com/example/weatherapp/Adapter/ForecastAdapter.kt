package com.example.weatherapp.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ForecastViewHolderBinding
import com.example.weatherapp.model.ForecastResponseApi
import java.text.SimpleDateFormat
import java.util.Calendar
import kotlin.math.roundToInt

class ForecastAdapter: RecyclerView.Adapter<ForecastAdapter.ViewHolder> (){
    private  lateinit var binding:ForecastViewHolderBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastAdapter.ViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        binding= ForecastViewHolderBinding.inflate(inflater,parent,false)
        return ViewHolder()
    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat", "DiscouragedApi")
    override fun onBindViewHolder(holder: ForecastAdapter.ViewHolder, position: Int) {
       val binding = ForecastViewHolderBinding.bind(holder.itemView)
        val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((differ.currentList[position].dtTxt.toString()))
        val calendar=Calendar.getInstance()
        if (date != null) {
            calendar.time=date
        }

        val dayOfWeekName=when(calendar.get(Calendar.DAY_OF_WEEK)){
            1->"Sun"
            2->"Mon"
            3->"Tue"
            4->"Wed"
            5->"Thu"
            6->"Fri"
            7->"Sat"
            else->"-"
        }
        binding.textDay.text=dayOfWeekName
        val hour=calendar.get(Calendar.HOUR_OF_DAY)
        val amPm=if(hour<12)"am" else "pm"
        val hour12=calendar.get(Calendar.HOUR)
        binding.textHour.text="$hour12$amPm"
        binding.textTemp.text= differ.currentList[position].main?.temp?.roundToInt().toString()+"°"

        val icon=when(differ.currentList[position].weather?.get(0)?.icon.toString()){
            "01d","0n"->"sunny"
            "02d","02n","03d","03n"->"cloudy_sunny"
            "04d","04n"->"cloudy"
            "09d","09n","10d","10n"->"rainy"
            "13d","13n"->"snowy"
            "11d","11n"->"storm"
            "50d","50n"->"windy"
            else->"sunny"
        }
        //truy cập tài nguyên trong res: binding.root.resources
        //muốn lấy một hình ảnh từ tài nguyên drawable: .getIdentifier(name_image,name_file,package_name)
        val drawableResourceId:Int = binding.root.resources.getIdentifier(icon,
           "drawable",
            binding.root.context.packageName )

        Glide.with(binding.root.context)
            .load(drawableResourceId)
            .error(R.drawable.cloudy)
            .into(binding.pic)

    }
    inner class ViewHolder() : RecyclerView.ViewHolder(binding.root)
    override fun getItemCount()= differ.currentList.size

    private val differCallback=object:DiffUtil.ItemCallback<ForecastResponseApi.data>(){
        override fun areItemsTheSame(
            oldItem: ForecastResponseApi.data,
            newItem: ForecastResponseApi.data
        ): Boolean {
           return  oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: ForecastResponseApi.data,
            newItem: ForecastResponseApi.data
        ): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this,differCallback)
}