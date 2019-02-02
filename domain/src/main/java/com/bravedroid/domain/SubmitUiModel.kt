package com.bravedroid.domain

class SubmitUiModel<T>(val state: State, val data: T, val error: Error? = null) {
    enum class State {
        LOADING, SUCCESS, ERROR
    }
}

sealed class Error(val message: String)
class NoInternetError  : Error("your internet connection is not enabled")
class InvalidCredentialsError : Error("Invalid login/password error!")
class ForbiddenError : Error("Forbidden access to the resources")
class ServerError : Error("Server error occurred")
class UnknownError : Error("Something unusual happened")
