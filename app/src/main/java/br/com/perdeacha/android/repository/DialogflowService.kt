package br.com.perdeacha.android.repository

import android.content.Context


import br.com.perdeacha.android.repository.RetrofitBase
import br.com.perdeacha.android.model.DialogflowRequest
import br.com.perdeacha.android.model.DialogflowResult
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*
import java.io.File


interface DialogflowService {

    @POST("message/text/send")
    @Headers("Content-Type: application/json")
    fun sendTextMessage(@Body request: DialogflowRequest): Call<DialogflowResult>

    @Multipart
    @POST("message/audio/send")
    fun sendAudioMessage(@Part("json") request: RequestBody, @Part file: MultipartBody.Part): Call<DialogflowResult>
}

class DialogflowRepository(context: Context, baseUrl: String) : RetrofitBase(context, baseUrl) {
    private val service = retrofit.create(DialogflowService::class.java)

    fun sentTextMessage(text: String, email: String, sessionId: String, callback: Callback<DialogflowResult>) {
        val request = DialogflowRequest(text, email, sessionId)
        service.sendTextMessage(request).enqueue(callback)
    }

}