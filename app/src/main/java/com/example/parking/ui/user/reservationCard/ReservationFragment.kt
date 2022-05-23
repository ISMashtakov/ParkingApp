package com.example.parking.ui.user.reservationCard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.parking.R
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class ReservationFragment : Fragment() {

    companion object {
        fun newInstance() = ReservationFragment()
    }

    private val viewModel by viewModel<ReservationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (arguments?.getString("spotId") != null && arguments?.getInt("spotNumber") != null) {
            viewModel.setSpotData(
                arguments?.getString("spotId")!!,
                arguments?.getInt("spotNumber")!!
            )
        }

        return inflater.inflate(R.layout.reservation_fragment, container, false)
    }

    private fun subscribeOnReservationButton(view: View) {
        val button: Button = view.findViewById(R.id.bookingButton)
        val driverIdText: TextInputEditText = view.findViewById(R.id.driverIdTextInput)
        val carIdText: TextInputEditText = view.findViewById(R.id.carIdTextInput)
        val startDateText: TextInputEditText = view.findViewById(R.id.startDateInput)
        val startTimeText: TextInputEditText = view.findViewById(R.id.startTimeInput)
        val endDateText: TextInputEditText = view.findViewById(R.id.endDateInput)
        val endTimeText: TextInputEditText = view.findViewById(R.id.endTimeInput)

        button.setOnClickListener{
            viewModel.reservation(
                driverId = driverIdText.text.toString(),
                carId = carIdText.text.toString(),
                startDate = Date(),
                endDate = Date()
            )
        }

    }

    private fun subscribeOnBackButton(view: View){
        val backButton: ImageButton = view.findViewById(R.id.backButton)

        backButton.setOnClickListener{
            parentFragmentManager.popBackStack()
        }
    }

    private fun subscribeOnSpotNumber(view: View){
        val title: TextView = view.findViewById(R.id.titleTextView)
        lifecycleScope.launch{
            viewModel.spotNumber.collect {
                title.text = resources.getString(R.string.parking_place, it)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeOnReservationButton(view)
        subscribeOnSpotNumber(view)
        subscribeOnBackButton(view)
    }

}