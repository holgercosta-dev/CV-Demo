package com.example.cv_demo.data.remote.mock

enum class Route(val path: String) {
    GET_CV_DATA("/cv/data"),
    GET_USER_PROFILE("/user/profile"),
    UNKNOWN("");

    companion object {
        fun fromPath(path: String): Route {
            return entries.find { it.path == path } ?: UNKNOWN
        }
    }
}