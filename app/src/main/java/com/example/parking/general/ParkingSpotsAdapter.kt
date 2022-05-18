package com.example.parking.general

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parking.R
import com.example.parking.data.parking_spot.ParkingSpot
import com.example.parking.databinding.ParkingSpotItemBinding

class ParkingSpotsAdapter(private val context: Context) :
    RecyclerView.Adapter<ParkingSpotsAdapter.ParkingSpotViewHolder>() {

    var spots: List<ParkingSpot> = (1..20).map {
        ParkingSpot(it.toString(), it, true)
    }
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = spots.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingSpotViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ParkingSpotItemBinding.inflate(inflater, parent, false)

        return ParkingSpotViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ParkingSpotViewHolder, position: Int) {
        val spot = spots[position]
        with(holder.binding) {
            name.text = context.resources.getString(R.string.parking_place, spot.parkingNumber)
        }
    }


    class ParkingSpotViewHolder(
        val binding: ParkingSpotItemBinding
    ) : RecyclerView.ViewHolder(binding.root)
}
