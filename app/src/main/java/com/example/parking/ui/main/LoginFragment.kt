package com.example.parking.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.parking.ParkingApp
import com.example.parking.R

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, LoginViewModelFactory(
            activity?.application as ParkingApp))[LoginViewModel::class.java]

        val enterButton = view.findViewById(R.id.enterButton) as Button
        val loginTextField = view.findViewById(R.id.loginTextField) as EditText
        val passwordTextField = view.findViewById(R.id.passwordTextField) as EditText

        fun signIn(){
            viewModel.signIn(
                login = loginTextField.text.toString(),
                password = passwordTextField.text.toString()
            )
        }

        loginTextField.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                passwordTextField.requestFocus()
            }
            true
        }

        passwordTextField.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                signIn()
            }
            true
        }

        enterButton.setOnClickListener{signIn()}

    }

}