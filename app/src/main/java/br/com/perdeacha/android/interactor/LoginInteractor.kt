package br.com.perdeacha.android.interactor

import android.text.TextUtils
import android.util.Patterns
import br.com.perdeacha.android.exception.ForgotException
import br.com.perdeacha.android.exception.LoginException
import br.com.perdeacha.android.exception.SignupException
import br.com.perdeacha.android.repository.LoginRepository
import kotlin.Exception

class LoginInteractor {

    val repo = LoginRepository()

    val EMPTY_EMAIL = "Email não informado"
    val EMPTY_PASSWORD = "Senha não informada"
    val EMPTY_SECOND_PASSWORD = "Segunda senha não informada"
    val INCORRECT_EMAIL = "Email fora do padrão"
    val NOT_IDENTIC_PASSWORD = "As Senhas não são idênticas"

    fun login(user: String, password: String, callback: (error: Exception?) -> Unit) {
        //VALIDAR REGRA DE NEGÓCIO
        if(TextUtils.isEmpty(user) || TextUtils.equals("null", user)){
            callback(LoginException(-9997, EMPTY_EMAIL))
            return
        }

        if(TextUtils.isEmpty(password) || TextUtils.equals("null", password)){
            callback(LoginException(-9997, EMPTY_PASSWORD))
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(user).matches()) {
            callback(LoginException(-9997, INCORRECT_EMAIL))
            return
        }

        repo.login(user, password, callback)
    }

    fun signup(user: String, password: String, repPassword: String, callback: (error: Exception?) -> Unit) {
        //TODO: VALIDAR REGRAS PARA CRIAR USUARIOS
        if(TextUtils.isEmpty(user) || TextUtils.equals("null", user)){
            callback(SignupException(-9997, EMPTY_EMAIL))
            return
        }

        if(TextUtils.isEmpty(password) || TextUtils.equals("null", password)){
            callback(SignupException(-9997, EMPTY_PASSWORD))
            return
        }

        if(TextUtils.isEmpty(repPassword) || TextUtils.equals("null", repPassword)){
            callback(SignupException(-9997, EMPTY_SECOND_PASSWORD))
            return
        }

        if(!TextUtils.equals(password, repPassword)){
            callback(SignupException(-9997, NOT_IDENTIC_PASSWORD))
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(user).matches()) {
            callback(SignupException(-9997, INCORRECT_EMAIL))
            return
        }

        repo.signup(user, password, callback)
    }

    fun forgot(user: String, callback: (error: Exception?) -> Unit) {
        //VALIDAR REGRA DE NEGÓCIO
        if(TextUtils.isEmpty(user) || TextUtils.equals("null", user)){
            callback(ForgotException(-9997, EMPTY_EMAIL))
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(user).matches()) {
            callback(ForgotException(-9997, INCORRECT_EMAIL))
            return
        }

        repo.forgot(user, callback)
    }
}