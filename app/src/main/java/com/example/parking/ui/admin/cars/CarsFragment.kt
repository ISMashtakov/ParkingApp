package com.example.parking.ui.admin.cars

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parking.R
import com.example.parking.data.parking_spot.ParkingSpot
import com.example.parking.general.ClickListener
import com.example.parking.ui.admin.cars.create.CarsCreateFragment
import com.example.parking.ui.admin.reservation.create.ReservationCreateFragment
import com.example.parking.ui.user.spot_card.SpotCardFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CarsFragment : Fragment() {

    companion object {
        fun newInstance() = CarsFragment()
    }

    private val viewModel by viewModel<CarsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.supportFragmentManager?.setFragmentResultListener(
            "createPage",
            this
        ) { _, bundle ->
            bundle.getBoolean("created").let {
                if (it)
                    viewModel.updateCars()
            }

        }

        return inflater.inflate(R.layout.cars_fragment, container, false)
    }

    private fun settingRecyclerView(view: View) {
        val recyclerView: RecyclerView = view.findViewById(R.id.carsRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = viewModel.carsAdapter

    }

    private fun subscribeOnAddButton(view: View) {
        val button: ImageButton = view.findViewById(R.id.addButton)
        button.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(
                    R.id.main_container,
                    CarsCreateFragment.newInstance(),
                    "CreateCar"
                )
                ?.addToBackStack("CreateCar")
                ?.commit()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingRecyclerView(view)
        subscribeOnAddButton(view)
    }

}