package com.example.parking.ui.user.reservationCard

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.parking.R
import com.example.parking.ui.views.Backdrop
import com.example.parking.utils.toDate
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDateTime
import java.util.*


class ReservationFragment : Fragment() {

    companion object {
        fun newInstance() = ReservationFragment()
    }

    private val viewModel by viewModel<ReservationViewModel>()
    private lateinit var backdrop: Backdrop

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

        return inflater.inflate(R.layout.reservation_create_fragment, container, false)
    }

    private fun subscribeOnReservationButton(view: View) {
        val button: Button = view.findViewById(R.id.bookingButton)
        val driverIdText: AutoCompleteTextView = view.findViewById(R.id.driverAutoComplete)
        val carIdText: AutoCompleteTextView = view.findViewById(R.id.carAutoComplete)
        val startDateText: TextInputEditText = view.findViewById(R.id.startDateInput)
        val endDateText: TextInputEditText = view.findViewById(R.id.endDateInput)

        button.setOnClickListener {
            if (driverIdText.text.isEmpty()
                || carIdText.text.isEmpty()
                || startDateText.text!!.isEmpty()
                || endDateText.text!!.isEmpty()
            ) {
                val toast = Toast.makeText(
                    context,
                    resources.getString(R.string.not_all_fields_filled),
                    Toast.LENGTH_SHORT
                )
                toast.show()
            } else {
                viewModel.reservation(
                    id = UUID.randomUUID().toString(),
                    name = driverIdText.text.toString(),
                    number = carIdText.text.toString(),
                    startDate = viewModel.dateFormat.parse(startDateText.text.toString())!!,
                    endDate = viewModel.dateFormat.parse(endDateText.text.toString())!!
                )
            }
        }

    }

    private fun subscribeOnBackButton(view: View) {
        val backButton: ImageButton = view.findViewById(R.id.backButton)

        backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun subscribeOnSpotNumber(view: View) {
        val title: TextView = view.findViewById(R.id.titleTextView)
        lifecycleScope.launch {
            viewModel.spotNumber.collect {
                title.text = resources.getString(R.string.parking_place, it)
            }
        }
    }

    private fun subscribeOnData(view: View) {
        val driver: AutoCompleteTextView = view.findViewById(R.id.driverAutoComplete)
        val car: AutoCompleteTextView = view.findViewById(R.id.carAutoComplete)

        lifecycleScope.launch {
            viewModel.cars.collect {
                val adapter: ArrayAdapter<String> = ArrayAdapter(
                    requireActivity() as Context,
                    R.layout.drop_down_item,
                    it.map { car -> car.registryNumber }
                )
                car.setAdapter(adapter)
            }
        }
        lifecycleScope.launch {
            viewModel.employees.collect {
                val adapter: ArrayAdapter<String> = ArrayAdapter(
                    requireActivity() as Context,
                    R.layout.drop_down_item,
                    it.map { employee -> employee.name }
                )
                driver.setAdapter(adapter)
            }
        }
    }

    private fun subscribeOnIsLoading() {
        lifecycleScope.launch {
            viewModel.isLoading.collect {
                if (it) {
                    backdrop.show()
                } else {
                    backdrop.dismiss()
                }
            }
        }
    }

    private fun showDateTimePicker(textInput: TextInputEditText) {
        val calendar = Calendar.getInstance()
        val dateSetListener =
            OnDateSetListener { _, year, month, dayOfMonth ->
                calendar[Calendar.YEAR] = year
                calendar[Calendar.MONTH] = month
                calendar[Calendar.DAY_OF_MONTH] = dayOfMonth
                val timeSetListener =
                    OnTimeSetListener { _, hourOfDay, minute ->
                        calendar[Calendar.HOUR_OF_DAY] = hourOfDay
                        calendar[Calendar.MINUTE] = minute

                        textInput.setText(viewModel.dateFormat.format(calendar.time))
                    }
                TimePickerDialog(
                    requireContext(), timeSetListener,
                    calendar[Calendar.HOUR_OF_DAY], calendar[Calendar.MINUTE], true
                ).show()
            }

        DatePickerDialog(
            requireContext(), dateSetListener,
            calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH]
        ).show()
    }

    private fun setUpDateEditText(view: View) {
        val startDateText: TextInputEditText = view.findViewById(R.id.startDateInput)
        val endDateText: TextInputEditText = view.findViewById(R.id.endDateInput)

        startDateText.setOnClickListener {
            showDateTimePicker(startDateText);
        }

        endDateText.setOnClickListener {
            showDateTimePicker(endDateText);
        }

        startDateText.setText(viewModel.dateFormat.format(LocalDateTime.now().plusMinutes(30).toDate()))
        endDateText.setText(viewModel.dateFormat.format(LocalDateTime.now().plusMinutes(90).toDate()))
    }

    private fun subscribeOnCreated() {
        lifecycleScope.launch {
            viewModel.onCreated.collect {
                if (it) {
                    val toast = Toast.makeText(
                        context,
                        resources.getString(R.string.created),
                        Toast.LENGTH_SHORT
                    )
                    toast.show()
                    parentFragmentManager.setFragmentResult(
                        "createPage", bundleOf(Pair("created", true))
                    )
                    parentFragmentManager.popBackStack()
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backdrop = Backdrop(activity)

        subscribeOnReservationButton(view)
        subscribeOnSpotNumber(view)
        subscribeOnBackButton(view)
        subscribeOnData(view)
        setUpDateEditText(view)
        subscribeOnIsLoading()
        subscribeOnCreated()
    }

}