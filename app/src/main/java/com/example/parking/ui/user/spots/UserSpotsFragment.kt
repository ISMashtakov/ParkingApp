package com.example.parking.ui.user.spots

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.parking.R

class UserSpotsFragment : Fragment() {

    companion object {
        fun newInstance() = UserSpotsFragment()
    }

    private lateinit var viewModel: UserSpotsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_locations_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserSpotsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}