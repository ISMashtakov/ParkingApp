package com.example.parking.ui.user.spot_card

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parking.R
import com.example.parking.ui.user.reservationCard.ReservationFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SpotCardFragment : Fragment() {

    companion object {
        fun newInstance() = SpotCardFragment()
    }

    private val viewModel by viewModel<SpotCardViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.getString("spotId")?.let { viewModel.spotId = it }
        arguments?.getInt("spotNumber")?.let { viewModel.spotNumber = it }
        return inflater.inflate(R.layout.spot_card_fragment, container, false)
    }

    private fun subscribeOnBackButton(view: View){
        val backButton: ImageButton = view.findViewById(R.id.backButton)

        backButton.setOnClickListener{
            parentFragmentManager.popBackStack()
        }
    }

    private fun subscribeOnBookingButton(view: View) {
        val button: Button = view.findViewById(R.id.bookingButton)
        button.setOnClickListener{
            val data = Bundle()
            data.putString("spotId", viewModel.spotId)
            data.putInt("spotNumber", viewModel.spotNumber)

            val fragment = ReservationFragment()
            fragment.arguments = data

            parentFragmentManager.beginTransaction()
                .replace(R.id.main_container, fragment, "Reservation ${viewModel.spotNumber}")
                .addToBackStack("Reservation ${viewModel.spotNumber}")
                .commit()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.reservationsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = viewModel.reservationAdapter

        val title: TextView = view.findViewById(R.id.titleTextView)
        title.text = resources.getString(R.string.parking_place, viewModel.spotNumber)

        subscribeOnBookingButton(view)
        subscribeOnBackButton(view)
    }

}