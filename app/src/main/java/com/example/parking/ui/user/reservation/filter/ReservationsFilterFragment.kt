package com.example.parking.ui.user.reservation.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.lifecycleScope
import com.example.parking.R
import com.example.parking.ui.user.reservation.UserReservationFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReservationsFilterFragment : Fragment() {

    companion object {
        fun newInstance() = ReservationsFilterFragment()
    }

    private val viewModel by viewModel<ReservationsFilterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.reservations_filter_fragment, container, false)
    }

    private fun subscribeOnOnlyFree(view: View) {
        val onlyFreeLayout: ConstraintLayout = view.findViewById(R.id.onlyFreeLayout)

        lifecycleScope.launch {
            viewModel.onlyFree.collect {
                for (child in onlyFreeLayout.children) {
                    child.isEnabled = it
                }
            }
        }
    }

    private fun subscribeOnOnlyFreeCheckBox(view: View){
        val onlyFreeCheckBox: CheckBox = view.findViewById(R.id.onlyFreeCheckBox)

        onlyFreeCheckBox.setOnClickListener{
            viewModel.clickOnCheckBox()
        }
    }

    private fun subscribeOnBackButton(view: View){
        val backButton: ImageButton = view.findViewById(R.id.backButton)

        backButton.setOnClickListener{
            activity?.supportFragmentManager?.setFragmentResult("filterData", bundleOf(Pair("enabled", viewModel.onlyFree.value)))
            parentFragmentManager.popBackStack()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeOnOnlyFreeCheckBox(view)
        subscribeOnOnlyFree(view)
        subscribeOnBackButton(view)

    }

}