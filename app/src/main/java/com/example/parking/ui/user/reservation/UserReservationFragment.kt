package com.example.parking.ui.user.reservation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parking.R
import com.example.parking.data.parking_spot.ParkingSpot
import com.example.parking.general.ClickListener
import com.example.parking.ui.user.spot_card.SpotCardFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserReservationFragment : Fragment() {

    companion object {
        fun newInstance() = UserReservationFragment()
    }

    private val viewModel by viewModel<UserReservationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_park_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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


}