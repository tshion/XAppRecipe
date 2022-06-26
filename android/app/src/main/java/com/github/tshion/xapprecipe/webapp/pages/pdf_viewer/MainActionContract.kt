package com.github.tshion.xapprecipe.webapp.pages.pdf_viewer

import com.github.tshion.xapprecipe.webapp.contracts.CancelableActionContract

interface MainActionContract : CancelableActionContract {

    fun loadPdf()

    fun loadPrev()

    fun loadNext()

    fun setup()
}
