package com.example.parking.ui.user

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.parking.R
import com.example.parking.ui.user.reservation.UserReservationFragment
import com.example.parking.ui.user.spots.UserSpotsFragment
import com.example.parking.ui.user.spots.UserSpotsViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserFragment : Fragment() {

    companion object {
        fun newInstance() = UserFragment()
    }

    private val viewModel by viewModel<UserViewModel>()
    private val userReservationFragment =UserReservationFragment.newInstance()
    private val userSpotsFragment = UserSpotsFragment.newInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_page_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNavigationView: BottomNavigationView = view.findViewById(R.id.user_bottom_navigation_view)

        if (!viewModel.isLoaded) {
            childFragmentManager.beginTransaction()
                .replace(R.id.user_menu_container, userReservationFragment)
                .commitNow()
            viewModel.isLoaded = true
        }

        bottomNavigationView.setOnItemSelectedListener{
            when(it.itemId){
                R.id.reservation_menu_item -> {
                    childFragmentManager.beginTransaction()
                        .replace(R.id.user_menu_container, userReservationFragment)
                        .addToBackStack(R.id.reservation_menu_item.toString())
                        .commit()
                }
                R.id.spots_menu_item -> {
                    childFragmentManager.beginTransaction()
                        .replace(R.id.user_menu_container, userSpotsFragment)
                        .addToBackStack(R.id.spots_menu_item.toString())
                        .commit()
                }
            }
            true
        }

    }

}