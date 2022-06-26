package com.github.tshion.xapprecipe.webapp_core.validators

/**
 * Web サイトのURL かどうかの検証
 */
internal object WebUriValidator {

    private val regexWebUri = """^https?://.*""".toRegex()


    fun isValid(target: String?) = regexWebUri.matches(target ?: "")
}
