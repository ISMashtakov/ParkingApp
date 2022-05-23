package com.example.parking.general

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parking.data.employees.Employee
import com.example.parking.databinding.ParkingSpotItemBinding

class EmployeesAdapter() :
    RecyclerView.Adapter<EmployeesAdapter.PeopleViewHolder>() {

    var employees: List<Employee> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = employees.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ParkingSpotItemBinding.inflate(inflater, parent, false)
        return PeopleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        val employee = employees[position]
        with(holder.binding) {
            name.text = employee.name
        }
    }


    class PeopleViewHolder(
        val binding: ParkingSpotItemBinding
    ) : RecyclerView.ViewHolder(binding.root)
}
