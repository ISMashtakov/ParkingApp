package com.example.parking.ui.user.spot_card

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parking.R
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.reservationsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = viewModel.reservationAdapter

        val title: TextView = view.findViewById(R.id.titleTextView)
        title.text = resources.getString(R.string.parking_place, viewModel.spotNumber)
    }

}