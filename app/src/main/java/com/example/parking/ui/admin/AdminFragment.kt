package com.example.parking.ui.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.parking.R
import com.example.parking.ui.admin.reservation.AdminReservationFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class AdminFragment : Fragment() {

    companion object {
        fun newInstance() = AdminFragment()
    }

    private val viewModel by viewModel<AdminViewModel>();
    private val adminReservationFragment = AdminReservationFragment.newInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.admin_page_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNavigationView: BottomNavigationView = view.findViewById(R.id.admin_bottom_navigation_view)

        if (!viewModel.isLoaded) {
            childFragmentManager.beginTransaction()
                .replace(R.id.admin_menu_container, adminReservationFragment)
                .commitNow()
            viewModel.isLoaded = true
        }

        bottomNavigationView.setOnItemSelectedListener{
            when(it.itemId){
                R.id.reservation_menu_item -> {
                    childFragmentManager.beginTransaction()
                        .replace(R.id.admin_menu_container, adminReservationFragment)
                        .addToBackStack(R.id.reservation_menu_item.toString())
                        .commit()
                }
                R.id.cars_menu_item -> {
//                    childFragmentManager.beginTransaction()
//                        .replace(R.id.admin_menu_container, userSpotsFragment)
//                        .addToBackStack(R.id.spots_menu_item.toString())
//                        .commit()
                }
                R.id.people_menu_item -> {
//                    childFragmentManager.beginTransaction()
//                        .replace(R.id.admin_menu_container, userSpotsFragment)
//                        .addToBackStack(R.id.spots_menu_item.toString())
//                        .commit()
                }
            }
            true
        }
    }

}
