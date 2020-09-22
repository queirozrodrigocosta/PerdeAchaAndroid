package br.com.perdeacha.android.exception

class LoginException(val code: Int, override val message: String?): Exception(message)