package work.shion.xapprecipe.pages.map_web

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.github.tshion.mktools_android.handleLifecycleEvents
import com.github.tshion.mktools_android.webview_builder.WebViewBuilder
import permissions.dispatcher.ktx.LocationPermission
import permissions.dispatcher.ktx.constructLocationPermissionRequest
import work.shion.androidpreparation.intentbuilder.LaunchPhoneIntentBuilder
import work.shion.xapprecipe.BuildConfig
import work.shion.xapprecipe.databinding.PagesMapWebBinding
import work.shion.xapprecipe.pages.top.MainFragment as TopFragment

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
                if (request.url?.scheme?.equals("tel", true) == true) {
                    val phoneNumber = request.url.toString().removePrefix("tel:")
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
