package com.example.cv_demo.data.remote.mock

enum class Route(val path: String) {
    GET_USER_PROFILE("/user/profile"),
    GET_SHOPPING_CART("/user/shopping-cart"),
    POST_VALIDATE_CREDIT_CARD("/orders/validate-credit-card"),
    POST_PURCHASE_ORDER("/orders/purchase"),
    UNKNOWN("");

    companion object {
        fun fromPath(path: String): Route {
            return entries.find { it.path == path } ?: UNKNOWN
        }
    }
}