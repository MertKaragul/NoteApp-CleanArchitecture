package com.mertkaragul.noteappcleanarchitecture.Common

abstract class Resource<T>(val data : T? = null, val error : String? = null){
    class Success<T>(data : T) : Resource<T>(data = data)
    class Loading<T>() : Resource<T>()
    class Error<T>(error: String) : Resource<T>(error = error)
}