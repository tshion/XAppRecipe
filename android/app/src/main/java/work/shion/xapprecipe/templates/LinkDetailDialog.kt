package work.shion.xapprecipe.templates

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.navArgs
import com.github.tshion.xapprecipe.databinding.TemplatesLinkDetailDialogBinding
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder

/**
 * リンク詳細ダイアログ
 *
 * ## Example
 * ### ダイアログの呼び出し
 * ``` kotlin
 * activity?.let { Navigation.findNavController(it, R.id.entrypoint) }
 *     ?.navigate(NavEntrypointDirections.navactShowLinkDetailDialog(
 *         requestKey = "request key",
 *         uri = "https://mokumokulog.netlify.app/",
 *     ))
 * ```
 *
 * ### ダイアログ選択結果の受け取り
 * ``` kotlin
 * class Xxx : Fragment() {
 *
 *     ......
 *
 *     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
 *         super.onViewCreated(view, savedInstanceState)
 *         setFragmentResultListener("request key") { _, _ ->
 *             // Do something
 *         }
 *     }
 *
 *     ......
 * }
 * ```
 */
class LinkDetailDialog : DialogFragment() {

    private val args by navArgs<LinkDetailDialogArgs>()
    private var binding: TemplatesLinkDetailDialogBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
                dismiss()
                setFragmentResult(args.requestKey, bundleOf())
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
