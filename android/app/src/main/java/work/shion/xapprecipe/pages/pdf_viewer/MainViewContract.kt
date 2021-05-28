package work.shion.xapprecipe.pages.pdf_viewer

import android.graphics.pdf.PdfRenderer

interface MainViewContract {

    fun reflectPdf(page: PdfRenderer.Page)
}
