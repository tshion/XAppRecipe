package com.github.tshion.xapprecipe_core.validators

internal object ToDoTaskValidator {

    fun preCheckTitle(value: String?): RuntimeException? {
        return if (value.isNullOrBlank()) {
            IllegalArgumentException()
        } else {
            null
        }
    }
}
