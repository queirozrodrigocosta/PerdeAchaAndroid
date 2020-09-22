package br.com.perdeacha.android.exception

class ForgotException(val code: Int, override val message: String?): Exception(message)