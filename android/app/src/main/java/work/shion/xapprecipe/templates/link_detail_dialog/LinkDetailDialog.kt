package work.shion.xapprecipe.templates.link_detail_dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import work.shion.xapprecipe.databinding.TemplatesLinkDetailDialogBinding

class LinkDetailDialog : DialogFragment() {

    private val args by navArgs<LinkDetailDialogArgs>()
    private var binding: TemplatesLinkDetailDialogBinding? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = TemplatesLinkDetailDialogBinding.inflate(
            inflater,
            container,
            false
        )
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        try {
            BarcodeEncoder().encodeBitmap(args.uri, BarcodeFormat.QR_CODE, 400, 400)
                .also { binding?.templatesLinkDetailDialogCode?.setImageBitmap(it) }

            binding?.templatesLinkDetailDialogClose?.setOnClickListener { dismiss() }

            binding?.templatesLinkDetailDialogDelete?.setOnClickListener {
                // TODO 選択結果の通知
            }
        } catch (ex: Error) {
            dismiss()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
        }
    }

    override fun onPause() {
        super.onPause()
        dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
