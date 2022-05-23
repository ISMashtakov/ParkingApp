package com.example.parking.general

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parking.R
import com.example.parking.data.cars.Car
import com.example.parking.databinding.CarsItemBinding

class CarsAdapter(private val context: Context) :
    RecyclerView.Adapter<CarsAdapter.CarsViewHolder>() {

    var cars: List<Car> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = cars.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CarsItemBinding.inflate(inflater, parent, false)
        return CarsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarsViewHolder, position: Int) {
        val car = cars[position]
        with(holder.binding) {
            number.text = car.registryNumber
            modelValue.text = car.model
            weightValue.text = context.resources.getString(
                R.string.weight_value,
                car.weight
            )
            lengthValue.text = context.resources.getString(
                R.string.length_value,
                car.length
            )
        }
    }


    class CarsViewHolder(
        val binding: CarsItemBinding
    ) : RecyclerView.ViewHolder(binding.root)
}
