package br.com.perdeacha.android.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.perdeacha.android.R
import br.com.perdeacha.android.exception.ForgotException
import br.com.perdeacha.android.exception.LoginException
import br.com.perdeacha.android.exception.SignupException
import br.com.perdeacha.android.interactor.LoginInteractor

class LoginViewModel(val app : Application) : AndroidViewModel(app) {

    val LOGIN_SUCCESS = app.getString(R.string.login_sucess_constant)
    val LOGIN_ERROR = app.getString(R.string.login_error_constant)

    private val loginInteractor = LoginInteractor()

    val checkPassword = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val email = MutableLiveData<String>()

    var result = MutableLiveData<String>()
    var msgError = String()

    fun login() {
        loginInteractor.login(email.value.toString(), password.value.toString()) { error ->
            if((error == null)){
                result.value = LOGIN_SUCCESS
            }else if (error is LoginException){
                if(error.code == -9998){
                    msgError = app.getString(R.string.login_error_msg)
                } else {
                    msgError = error.localizedMessage
                }
                result.value = LOGIN_ERROR
            }
        }
    }

    fun signup() {
        loginInteractor.signup(email.value.toString(), password.value.toString(), checkPassword.value.toString()) { error ->

            if((error == null)){
                result.value = LOGIN_SUCCESS
            }else if (error is SignupException){
                if(error.code == -9998){
                    msgError = app.getString(R.string.signup_error_msg)
                } else {
                    msgError = error.localizedMessage
                }
                result.value = LOGIN_ERROR
            }


        }
    }

    fun forgot() {
        loginInteractor.forgot(email.value.toString()) { error ->
            if((error == null)){
                result.value = LOGIN_SUCCESS
            }else if (error is ForgotException){
                if(error.code == -9998){
                    msgError = app.getString(R.string.forgot_error_msg)
                } else {
                    msgError = error.localizedMessage
                }
                result.value = LOGIN_ERROR
            }
        }
    }
}