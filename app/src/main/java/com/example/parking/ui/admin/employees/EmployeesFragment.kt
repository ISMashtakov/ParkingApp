package com.example.parking.ui.admin.employees

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parking.R
import com.example.parking.ui.admin.cars.create.CarsCreateFragment
import com.example.parking.ui.admin.employees.create.EmployeeCreateFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class EmployeesFragment : Fragment() {

    companion object {
        fun newInstance() = EmployeesFragment()
    }

    private val viewModel by viewModel<EmployeesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.supportFragmentManager?.setFragmentResultListener(
            "createPage",
            this
        ) { _, bundle ->
            bundle.getBoolean("created").let {
                if (it)
                    viewModel.updateEmployees()
            }

        }

        return inflater.inflate(R.layout.cars_fragment, container, false)
    }

    private fun settingRecyclerView(view: View) {
        val recyclerView: RecyclerView = view.findViewById(R.id.carsRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = viewModel.employeesAdapter

    }

    private fun subscribeOnAddButton(view: View) {
        val button: ImageButton = view.findViewById(R.id.addButton)
        button.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(
                    R.id.main_container,
                    EmployeeCreateFragment.newInstance(),
                    "CreateEmployee"
                )
                ?.addToBackStack("CreateEmployee")
                ?.commit()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        settingRecyclerView(view)
        subscribeOnAddButton(view)
    }

}