package com.example.parking.ui.user.spots

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parking.R
import com.example.parking.data.parking_spot.ParkingSpot
import com.example.parking.general.ClickListener
import com.example.parking.ui.user.reservation.UserReservationFragment
import com.example.parking.ui.user.reservation.filter.ReservationsFilterFragment
import com.example.parking.ui.user.spot_card.SpotCardFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserSpotsFragment : Fragment() {
    companion object {
        fun newInstance() = UserSpotsFragment()
    }

    private val viewModel by viewModel<UserSpotsViewModel>()

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

        return inflater.inflate(R.layout.user_spots_fragment, container, false)
    }

    private fun SettingRecyclerView(view: View) {
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

    private fun SubscribeOnFilter(view: View) {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        SettingRecyclerView(view)

        SubscribeOnFilter(view)

    }
}