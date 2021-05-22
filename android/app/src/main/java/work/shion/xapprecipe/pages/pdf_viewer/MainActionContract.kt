package work.shion.xapprecipe.pages.pdf_viewer

import work.shion.xapprecipe.contracts.CancelableActionContract

interface MainActionContract : CancelableActionContract {

    fun loadPdf()
}
