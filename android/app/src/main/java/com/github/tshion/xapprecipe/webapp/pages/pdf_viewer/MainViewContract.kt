package com.github.tshion.xapprecipe.webapp.pages.pdf_viewer

import android.graphics.pdf.PdfRenderer

interface MainViewContract {

    fun reflectPdf(page: PdfRenderer.Page)
}
