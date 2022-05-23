package com.example.parking.general

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parking.R
import com.example.parking.data.reservation.Reservation
import com.example.parking.databinding.ReservaionItemBinding
import java.text.SimpleDateFormat
import java.util.*

class ReservationAdapter(private val context: Context) : RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder>(){

    var reservations: List<Reservation> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = reservations.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ReservaionItemBinding.inflate(inflater, parent, false)
        return ReservationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReservationViewHolder, position: Int) {
        val reservation = reservations[position]
        val dateFormat = SimpleDateFormat("dd MMM HH:mm", Locale.US)
        with(holder.binding){
            nameTextView.text = context.resources.getString(
                R.string.booking_name,
                reservation.employee?.name
            )
            dateTextView.text = context.resources.getString(
                R.string.booking_date,
                dateFormat.format(reservation.startTime),
                dateFormat.format(reservation.endTime)
            )
        }
    }


    class ReservationViewHolder(
        val binding: ReservaionItemBinding
    ) : RecyclerView.ViewHolder(binding.root)
}
