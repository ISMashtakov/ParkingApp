package com.example.parking.ui.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.parking.R
import com.example.parking.data.auth.Role
import com.example.parking.ui.views.Backdrop
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private val viewModel by viewModel<LoginViewModel>()
    private lateinit var backdrop: Backdrop


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    private fun hideKeyboard(view: View){
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun subscribeOnAuth() {
        viewModel.runCoroutine {
            viewModel.onAuth.collect {
                when (it) {
                    Role.UNAUTHORIZED -> {
                        val toast = Toast.makeText(
                            context,
                            resources.getString(R.string.login_error),
                            Toast.LENGTH_SHORT
                        )
                        toast.show()
                    }
                    Role.ADMIN -> {
                        Log.e("OPEN", "ADMIN")
                        TODO()
                    }
                    Role.USER -> {
                        Log.e("OPEN", "USER")
                        TODO()
                    }
                }
            }
        }
    }

    private fun subscribeOnIsLoading() {
        viewModel.runCoroutine {
            viewModel.isLoading.collect {
                if (it) {
                    backdrop.show()
                } else {
                    backdrop.dismiss()
                }
            }
        }
    }

    private fun setListenersToViews(view: View) {
        val enterButton = view.findViewById(R.id.enterButton) as Button
        val loginTextField = view.findViewById(R.id.loginTextField) as EditText
        val passwordTextField = view.findViewById(R.id.passwordTextField) as EditText

        fun signIn() {
            viewModel.signIn(
                login = loginTextField.text.toString(),
                password = passwordTextField.text.toString()
            )
        }

        loginTextField.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                passwordTextField.requestFocus()
                return@setOnKeyListener true
            }
            false
        }

        passwordTextField.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                hideKeyboard(view)
                signIn()
                return@setOnKeyListener true
            }
            false
        }

        enterButton.setOnClickListener { signIn() }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backdrop = Backdrop(this.activity)

        subscribeOnAuth()
        subscribeOnIsLoading()
        setListenersToViews(view)
    }

}