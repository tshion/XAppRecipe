package work.shion.xapprecipe.pages.map_web

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import permissions.dispatcher.ktx.LocationPermission
import permissions.dispatcher.ktx.constructLocationPermissionRequest
import work.shion.androidpreparation.intentbuilder.LaunchPhoneIntentBuilder
import work.shion.androidpreparation.webviewbuilder.WebViewBuilder
import work.shion.xapprecipe.BuildConfig
import work.shion.xapprecipe.databinding.PagesMapWebBinding

/**
 * WEB 版地図表示
 */
class MainFragment : Fragment() {

    private var binding: PagesMapWebBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PagesMapWebBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Header
        binding?.pagesMapWebHeader?.apply {
            setupTapReloadListener {
                binding?.pagesMapWebBrowser?.reload()
            }
        }

        // Web
        val builder = WebViewBuilder()
            .geolocationEnabled(true)
            .javaScriptEnabled(true)
            .onGeolocationPermissionsShowPrompt { origin, callback ->
                constructLocationPermissionRequest(
                    LocationPermission.FINE,
                    onNeverAskAgain = { callback?.invoke(origin, false, false) },
                    onPermissionDenied = { callback?.invoke(origin, false, false) },
                    onShowRationale = { it.proceed() },
                ) {
                    callback?.invoke(origin, true, false)
                }.launch()
            }
            .shouldOverrideUrlLoading { _, request ->
                if (request?.url?.scheme?.equals("tel", true) == true) {
                    LaunchPhoneIntentBuilder()
                        .phoneNumber(request.url.path)
                        .build()
                        .also { startActivity(it) }
                } else {
                    request?.url?.also {
                        CustomTabsIntent.Builder()
                            .build()
                            .launchUrl(requireContext(), it)
                    }
                }
                return@shouldOverrideUrlLoading true
            }

        binding?.pagesMapWebBrowser?.also {
            builder.into(it)
                .loadUrl(BuildConfig.URL_MAP)
        }
    }

    override fun onResume() {
        binding?.pagesMapWebBrowser?.onResume()
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding?.pagesMapWebBrowser?.onPause()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        binding?.pagesMapWebBrowser?.destroy()
        super.onDestroy()
    }
}
