package com.bravedroid.domain

class SubmitUiModel<T>(val responseState: ResponseState, val data: T?, val responseError: ResponseError? = null) {
    enum class ResponseState {
        LOADING, SUCCESS, ERROR
    }
}

sealed class ResponseError(val message: String)
class NoInternetResponseError  : ResponseError("your internet connection is not enabled")
class InvalidCredentialsResponseError : ResponseError("Invalid login/password responseError!")
class ForbiddenResponseError : ResponseError("Forbidden access to the resources")
class ServerResponseError : ResponseError("Server responseError occurred")
class UnknownResponseError : ResponseError("Something unusual happened")
