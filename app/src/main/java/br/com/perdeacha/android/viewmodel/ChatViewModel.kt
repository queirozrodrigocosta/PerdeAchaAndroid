package br.com.perdeacha.android.viewmodel

import android.app.Application
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.perdeacha.android.R
import br.com.perdeacha.android.exception.ForgotException
import br.com.perdeacha.android.exception.LoginException
import br.com.perdeacha.android.exception.SignupException
import br.com.perdeacha.android.interactor.LoginInteractor
import br.com.perdeacha.android.model.DialogflowResult
import br.com.perdeacha.android.repository.DialogflowRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ChatViewModel(val app : Application) : AndroidViewModel(app) {

    val message = MutableLiveData<String>()

    var result = MutableLiveData<String>()

    fun send() {
        DialogflowRepository(app.baseContext, "https://dialogflow-server-perdeacha.herokuapp.com/api/").sentTextMessage(message.value.toString(), "asd@asd.com", UUID.randomUUID().toString(), object : Callback<DialogflowResult> {

            override fun onResponse(call: Call<DialogflowResult>, response: Response<DialogflowResult>) {
                result.value = response.message().toString();
            }

            override fun onFailure(call: Call<DialogflowResult>, t: Throwable) {
                Toast.makeText(app.baseContext, t.localizedMessage, Toast.LENGTH_LONG).show()
            }
        })


    }

}