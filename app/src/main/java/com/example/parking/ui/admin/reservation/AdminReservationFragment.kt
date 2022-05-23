package com.example.parking.ui.admin.reservation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parking.R
import com.example.parking.data.parking_spot.ParkingSpot
import com.example.parking.general.ClickListener
import com.example.parking.ui.admin.reservation.create.ReservationCreateFragment
import com.example.parking.ui.user.reservation.filter.ReservationsFilterFragment
import com.example.parking.ui.user.spot_card.SpotCardFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AdminReservationFragment : Fragment() {

    companion object {
        fun newInstance() = AdminReservationFragment()
    }

    private val viewModel by viewModel<AdminReservationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.supportFragmentManager?.setFragmentResultListener(
            "filterData",
            this
        ) { _, bundle ->
            viewModel.setFilterSettings(bundle)
        }

        activity?.supportFragmentManager?.setFragmentResultListener(
            "createPage",
            this
        ) { _, bundle ->
            bundle.getBoolean("created").let {
                if (it)
                    viewModel.updateSpots()
            }

        }

        return inflater.inflate(R.layout.admin_reservation_fragment, container, false)
    }

    private fun settingRecyclerView(view: View) {
        val recyclerView: RecyclerView = view.findViewById(R.id.spotsRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = viewModel.parkingSpotsAdapter

        viewModel.parkingSpotsAdapter.clickListener = object : ClickListener {
            override fun onClick(spot: ParkingSpot) {
                val data = Bundle()
                data.putString("spotId", spot.id)
                data.putInt("spotNumber", spot.parkingNumber)

                val card = SpotCardFragment()
                card.arguments = data

                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.main_container, card, "Spot${spot.parkingNumber}")
                    ?.addToBackStack("Spot${spot.parkingNumber}")
                    ?.commit()
            }
        }
    }

    private fun subscribeOnFilter(view: View) {
        val filterButton: ImageButton = view.findViewById(R.id.filterButton)

        filterButton.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.main_container, ReservationsFilterFragment.newInstance(), "Filter")
                ?.addToBackStack("Filter")
                ?.commit()
        }

        lifecycleScope.launch {
            viewModel.filterIsEnabled.collect {
                filterButton.setImageDrawable(
                    if (it)
                        ResourcesCompat.getDrawable(resources, R.drawable.filter_activ, null)
                    else
                        ResourcesCompat.getDrawable(resources, R.drawable.filter, null)
                )
            }
        }
    }

    private fun subscribeOnAddButton(view: View) {
        val button: ImageButton = view.findViewById(R.id.addButton)
        button.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(
                    R.id.main_container,
                    ReservationCreateFragment.newInstance(),
                    "CreateReservation"
                )
                ?.addToBackStack("CreateReservation")
                ?.commit()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingRecyclerView(view)
        subscribeOnAddButton(view)
        subscribeOnFilter(view)

    }
}