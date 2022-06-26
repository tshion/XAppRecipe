package com.github.tshion.xapprecipe.webapp_core.usecases

import java.io.File

interface ShowPdfUseCaseContract {

    suspend fun load(
        cacheName: String,
        url: String,
    ): File?
}
