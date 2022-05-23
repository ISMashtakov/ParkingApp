package com.example.parking.general

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parking.R
import com.example.parking.data.parking_spot.ParkingSpot
import com.example.parking.databinding.ParkingSpotItemBinding

interface ClickListener{
    fun onClick(spot: ParkingSpot)
}

class ParkingSpotsAdapter(private val context: Context) :
    RecyclerView.Adapter<ParkingSpotsAdapter.ParkingSpotViewHolder>(), View.OnClickListener{

    var clickListener: ClickListener? = null

//    var spots: List<ParkingSpot> = (1..20).map {
//        ParkingSpot(it.toString(), it, true)
//    }
    var spots: List<ParkingSpot> =  emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onClick(v: View) {
        clickListener?.onClick(v.tag as ParkingSpot)
    }

    override fun getItemCount(): Int = spots.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingSpotViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ParkingSpotItemBinding.inflate(inflater, parent, false)
        binding.root.setOnClickListener(this)
        return ParkingSpotViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ParkingSpotViewHolder, position: Int) {
        val spot = spots[position]

        holder.itemView.tag = spot
        with(holder.binding) {

            name.text = context.resources.getString(R.string.parking_place, spot.parkingNumber)
        }
    }


    class ParkingSpotViewHolder(
        val binding: ParkingSpotItemBinding
    ) : RecyclerView.ViewHolder(binding.root)
}
