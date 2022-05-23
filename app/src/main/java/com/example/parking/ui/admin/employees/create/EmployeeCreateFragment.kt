package com.example.parking.ui.admin.employees.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import com.example.parking.R
import com.example.parking.ui.views.Backdrop
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class EmployeeCreateFragment : Fragment() {

    companion object {
        fun newInstance() = EmployeeCreateFragment()
    }

    private val viewModel by viewModel<EmployeeCreateViewModel>()
    private lateinit var backdrop: Backdrop

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.employee_create_fragment, container, false)
    }

    private fun subscribeOnBackButton(view: View) {
        val backButton: ImageButton = view.findViewById(R.id.backButton)

        backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun subscribeOnCreateButton(view: View) {
        val backButton: Button = view.findViewById(R.id.createButton)
        val nameText: TextInputEditText = view.findViewById(R.id.nameTextInput)

        try {
            backButton.setOnClickListener {
                viewModel.createEmployee(
                    UUID.randomUUID().toString(),
                    nameText.text.toString(),
                )
            }
        } catch (ex: Exception) {
        }
    }

    private fun subscribeOnIsCreating() {
        lifecycleScope.launch {
            viewModel.isCreating.collect {
                if (it) {
                    backdrop.show()
                } else {
                    backdrop.dismiss()
                }
            }
        }
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

        backdrop = Backdrop(this.activity)

        subscribeOnBackButton(view)
        subscribeOnCreateButton(view)
        subscribeOnIsCreating()
        subscribeOnCreated()
    }
}