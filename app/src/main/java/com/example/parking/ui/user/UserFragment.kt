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
import com.google.android.material.bottomnavigation.BottomNavigationView

class UserFragment : Fragment() {

    companion object {
        fun newInstance() = UserFragment()
    }

    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_page_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val bottomNavigationView: BottomNavigationView = view.findViewById(R.id.user_bottom_navigation_view)

        childFragmentManager.beginTransaction()
            .replace(R.id.user_menu_container, UserReservationFragment.newInstance())
            .commitNow()

        bottomNavigationView.setOnItemSelectedListener{
            when(it.itemId){
                R.id.reservation_menu_item -> {
                    childFragmentManager.beginTransaction()
                        .replace(R.id.user_menu_container, UserReservationFragment.newInstance())
                        .addToBackStack(R.id.reservation_menu_item.toString())
                        .commit()
                }
                R.id.spots_menu_item -> {
                    childFragmentManager.beginTransaction()
                        .replace(R.id.user_menu_container, UserSpotsFragment.newInstance())
                        .addToBackStack(R.id.spots_menu_item.toString())
                        .commit()
                }
                R.id.settings_menu_item -> {}
            }
            true
        }

    }

}