package com.example.parking.ui.main

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.parking.R
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private val viewModel by viewModel<LoginViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    fun subscribeForAuthentication(){
        viewModel.runCoroutine {
            viewModel.authentication.role.collect {
                Log.e("ROLE", it.name)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeForAuthentication()

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