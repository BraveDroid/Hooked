package com.bravedroid.domain

class SubmitUiModel<T>(val responseState: ResponseState, val data: T?, val responseError: ResponseError? = null) {
    enum class ResponseState {
        LOADING, SUCCESS, ERROR
    }
}

fun createSubmitUiModel(
    responseState: SubmitUiModel.ResponseState?,
    story: Story? = null,
    error: ResponseError? = null
): SubmitUiModel<Story> =
    SubmitUiModel(responseState!!, story, error)


sealed class ResponseError(val message: String)
class NoInternetResponseError  : ResponseError("your internet connection is not enabled")
class InvalidCredentialsResponseError : ResponseError("Invalid login/password responseError!")
class ForbiddenResponseError : ResponseError("Forbidden access to the resources")
class ServerResponseError : ResponseError("Server responseError occurred")
class UnknownResponseError : ResponseError("Something unusual happened")
