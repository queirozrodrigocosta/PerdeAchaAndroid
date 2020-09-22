package br.com.perdeacha.android.repository

import br.com.perdeacha.android.exception.ForgotException
import br.com.perdeacha.android.exception.LoginException
import br.com.perdeacha.android.exception.SignupException
import com.google.firebase.auth.FirebaseAuth

class LoginRepository {

    fun login(user: String, password: String, callback: (error: Exception?) -> Unit) {
        //EFETUAR CHAMADA SERVIDOR DE AUTENTICACAO
        val auth = FirebaseAuth.getInstance()

        val task = auth.signInWithEmailAndPassword(user, password)
        task.addOnCompleteListener { result ->
            if(result.isSuccessful){
                callback(null)
            } else {
                callback(LoginException(-9998, result.exception?.localizedMessage))
            }
        }
    }

    fun signup(user: String, password: String, callback: (error: Exception?) -> Unit) {
        //EFETUAR CHAMADA SERVIDOR DE AUTENTICACAO
        val auth = FirebaseAuth.getInstance()

        val task = auth.createUserWithEmailAndPassword(user, password)
        task.addOnCompleteListener { result ->
            if(result.isSuccessful){
                callback(null)
            } else {
                callback(SignupException(-9998, result.exception?.localizedMessage))
            }
        }
    }

    fun forgot(user: String, callback: (error: Exception?) -> Unit) {
        //EFETUAR CHAMADA SERVIDOR DE AUTENTICACAO
        val auth = FirebaseAuth.getInstance()

        val task = auth.sendPasswordResetEmail (user)
        task.addOnCompleteListener { result ->
            if(result.isSuccessful){
                callback(null)
            } else {
                callback(ForgotException(-9998, result.exception?.localizedMessage))
            }
        }
    }
}