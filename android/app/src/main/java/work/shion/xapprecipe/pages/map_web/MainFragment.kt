package work.shion.xapprecipe.pages.map_web

import android.os.Bundle
import android.view.View
import android.webkit.WebView.SCHEME_TEL
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.github.tshion.mktools_android.handleLifecycleEvents
import com.github.tshion.mktools_android.webview_builder.WebViewBuilder
import com.github.tshion.xapprecipe.BuildConfig
import com.github.tshion.xapprecipe.R
import com.github.tshion.xapprecipe.databinding.PagesMapWebBinding
import permissions.dispatcher.ktx.LocationPermission
import permissions.dispatcher.ktx.constructLocationPermissionRequest
import work.shion.androidpreparation.intentbuilder.LaunchPhoneIntentBuilder
import work.shion.xapprecipe.pages.top.MainFragment as TopFragment

/**
 * WEB 版地図表示
 */
class MainFragment : Fragment(R.layout.pages_map_web) {

    private var binding: PagesMapWebBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = PagesMapWebBinding.bind(view)

        // Header
        binding?.pagesMapWebHeader?.apply {
            setupTapMenuListener {
                (parentFragment as? NavHostFragment)
                    ?.let { it.parentFragment as? TopFragment }
                    ?.openMenu()
            }

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
                    onNeverAskAgain = { callback.invoke(origin, false, false) },
                    onPermissionDenied = { callback.invoke(origin, false, false) },
                    onShowRationale = { it.proceed() },
                ) {
                    callback.invoke(origin, true, false)
                }.launch()
            }
            .shouldOverrideUrlLoading { _, request ->
                if (request.url?.scheme?.startsWith(SCHEME_TEL, true) == true) {
                    val phoneNumber = request.url.toString().removePrefix(SCHEME_TEL)
                    LaunchPhoneIntentBuilder()
                        .phoneNumber(phoneNumber)
                        .build()
                        .also { startActivity(it) }
                } else {
                    request.url?.also {
                        CustomTabsIntent.Builder()
                            .build()
                            .launchUrl(requireContext(), it)
                    }
                }
                return@shouldOverrideUrlLoading true
            }

        binding?.pagesMapWebBrowser?.also {
            it.handleLifecycleEvents(viewLifecycleOwner)
            builder.into(it)
                .loadUrl(BuildConfig.URL_MAP)
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}
