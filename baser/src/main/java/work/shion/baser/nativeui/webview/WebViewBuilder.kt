package work.shion.baser.nativeui.webview

import android.os.Build
import android.webkit.WebSettings
import android.webkit.WebView


/**
 * WebView 設定ビルダー
 */
class WebViewBuilder internal constructor() {

    /**
     * 指定のWebView に設定を反映する
     */
    fun into(target: WebView) = target.apply {
        with(settings) {
            allowContentAccess = mAllowContentAccess ?: allowContentAccess
            allowFileAccess = mAllowFileAccess ?: allowFileAccess
            allowFileAccessFromFileURLs = mAllowFileAccessFromFileURLs
                    ?: allowFileAccessFromFileURLs
            allowUniversalAccessFromFileURLs = mAllowUniversalAccessFromFileURLs
                    ?: allowUniversalAccessFromFileURLs
            if (mAppCacheEnabled != null) setAppCacheEnabled(mAppCacheEnabled!!)
            if (mAppCachePath != null) setAppCachePath(mAppCachePath)
            blockNetworkImage = mBlockNetworkImage ?: blockNetworkImage
            blockNetworkLoads = mBlockNetworkLoads ?: blockNetworkLoads
            builtInZoomControls = mBuiltInZoomControls ?: builtInZoomControls
            cacheMode = mCacheMode ?: cacheMode
            cursiveFontFamily = mCursiveFontFamily ?: cursiveFontFamily
            databaseEnabled = mDatabaseEnabled ?: databaseEnabled
            defaultFixedFontSize = mDefaultFixedFontSize ?: defaultFontSize
            defaultFontSize = mDefaultFontSize ?: defaultFontSize
            defaultTextEncodingName = mDefaultTextEncodingName ?: defaultTextEncodingName
            if (24 <= Build.VERSION.SDK_INT) disabledActionModeMenuItems = mDisabledActionModeMenuItems
                    ?: disabledActionModeMenuItems
            displayZoomControls = mDisplayZoomControls ?: displayZoomControls
            domStorageEnabled = mDomStorageEnabled ?: domStorageEnabled
            fantasyFontFamily = mFantasyFontFamily ?: fantasyFontFamily
            fixedFontFamily = mFixedFontFamily ?: fixedFontFamily
            if (29 <= Build.VERSION.SDK_INT) forceDark = mForceDark ?: forceDark
            if (mGeolocationEnabled != null) setGeolocationEnabled(mGeolocationEnabled!!)
            javaScriptCanOpenWindowsAutomatically = mJavaScriptCanOpenWindowsAutomatically
                    ?: javaScriptCanOpenWindowsAutomatically
            javaScriptEnabled = mJavaScriptEnabled ?: javaScriptEnabled
            layoutAlgorithm = mLayoutAlgorithm ?: layoutAlgorithm
            loadWithOverviewMode = mLoadWithOverviewMode ?: loadWithOverviewMode
            loadsImagesAutomatically = mLoadsImagesAutomatically ?: loadsImagesAutomatically
            mediaPlaybackRequiresUserGesture = mMediaPlaybackRequiresUserGesture
                    ?: mediaPlaybackRequiresUserGesture
            minimumFontSize = mMinimumFontSize ?: minimumFontSize
            minimumLogicalFontSize = mMinimumLogicalFontSize ?: minimumLogicalFontSize
            mixedContentMode = mMixedContentMode ?: mixedContentMode
            if (mNeedInitialFocus != null) setNeedInitialFocus(mNeedInitialFocus!!)
            if (23 <= Build.VERSION.SDK_INT) offscreenPreRaster = mOffscreenPreRaster
                    ?: offscreenPreRaster
            if (26 <= Build.VERSION.SDK_INT) safeBrowsingEnabled = mSafeBrowsingEnabled
                    ?: safeBrowsingEnabled
            sansSerifFontFamily = mSansSerifFontFamily ?: sansSerifFontFamily
            serifFontFamily = mSerifFontFamily ?: serifFontFamily
            standardFontFamily = mStandardFontFamily ?: standardFontFamily
            setSupportMultipleWindows(mSupportMultipleWindows ?: supportMultipleWindows())
            setSupportZoom(mSupportZoom ?: supportZoom())
            textZoom = mTextZoom ?: textZoom
            useWideViewPort = mUseWideViewPort ?: useWideViewPort
            userAgentString = mUserAgentString ?: userAgentString
        }
    }


    /**
     * WebSettings 関連
     * @see android.webkit.WebSettings
     * @see <a href="https://developer.android.com/reference/kotlin/android/webkit/WebSettings.html">Android Developers</a>
     */

    private var mAllowContentAccess: Boolean? = null
    private var mAllowFileAccess: Boolean? = null
    private var mAllowFileAccessFromFileURLs: Boolean? = null
    private var mAllowUniversalAccessFromFileURLs: Boolean? = null
    private var mAppCacheEnabled: Boolean? = null
    private var mAppCachePath: String? = null
    private var mBlockNetworkImage: Boolean? = null
    private var mBlockNetworkLoads: Boolean? = null
    private var mBuiltInZoomControls: Boolean? = null
    private var mCacheMode: Int? = null
    private var mCursiveFontFamily: String? = null
    private var mDatabaseEnabled: Boolean? = null
    private var mDefaultFixedFontSize: Int? = null
    private var mDefaultFontSize: Int? = null
    private var mDefaultTextEncodingName: String? = null
    private var mDisabledActionModeMenuItems: Int? = null
    private var mDisplayZoomControls: Boolean? = null
    private var mDomStorageEnabled: Boolean? = null
    private var mFantasyFontFamily: String? = null
    private var mFixedFontFamily: String? = null
    private var mForceDark: Int? = null
    private var mGeolocationEnabled: Boolean? = null
    private var mJavaScriptCanOpenWindowsAutomatically: Boolean? = null
    private var mJavaScriptEnabled: Boolean? = null
    private var mLayoutAlgorithm: WebSettings.LayoutAlgorithm? = null
    private var mLoadWithOverviewMode: Boolean? = null
    private var mLoadsImagesAutomatically: Boolean? = null
    private var mMediaPlaybackRequiresUserGesture: Boolean? = null
    private var mMinimumFontSize: Int? = null
    private var mMinimumLogicalFontSize: Int? = null
    private var mMixedContentMode: Int? = null
    private var mNeedInitialFocus: Boolean? = null
    private var mOffscreenPreRaster: Boolean? = null
    private var mSafeBrowsingEnabled: Boolean? = null
    private var mSansSerifFontFamily: String? = null
    private var mSerifFontFamily: String? = null
    private var mStandardFontFamily: String? = null
    private var mSupportMultipleWindows: Boolean? = null
    private var mSupportZoom: Boolean? = null
    private var mTextZoom: Int? = null
    private var mUseWideViewPort: Boolean? = null
    private var mUserAgentString: String? = null

    fun allowContentAccess(value: Boolean) = this.apply { mAllowContentAccess = value }
    fun allowFileAccess(value: Boolean) = this.apply { mAllowFileAccess = value }
    fun allowFileAccessFromFileURLs(value: Boolean) = this.apply { mAllowFileAccessFromFileURLs = value }
    fun allowUniversalAccessFromFileURLs(value: Boolean) = this.apply { mAllowUniversalAccessFromFileURLs = value }
    fun appCacheEnabled(value: Boolean) = this.apply { mAppCacheEnabled = value }
    fun appCachePath(value: String) = this.apply { mAppCachePath = value }
    fun blockNetworkImage(value: Boolean) = this.apply { mBlockNetworkImage = value }
    fun blockNetworkLoads(value: Boolean) = this.apply { mBlockNetworkLoads = value }
    fun builtInZoomControls(value: Boolean) = this.apply { mBuiltInZoomControls = value }
    fun cacheMode(value: Int) = this.apply { mCacheMode = value }
    fun cursiveFontFamily(value: String) = this.apply { mCursiveFontFamily = value }
    fun databaseEnabled(value: Boolean) = this.apply { mDatabaseEnabled = value }
    fun defaultFixedFontSize(value: Int) = this.apply { mDefaultFixedFontSize = value }
    fun defaultFontSize(value: Int) = this.apply { mDefaultFontSize = value }
    fun defaultTextEncodingName(value: String) = this.apply { mDefaultTextEncodingName = value }
    fun disabledActionModeMenuItems(value: Int) = this.apply { mDisabledActionModeMenuItems = value }
    fun displayZoomControls(value: Boolean) = this.apply { mDisplayZoomControls = value }
    fun domStorageEnabled(value: Boolean) = this.apply { mDomStorageEnabled = value }
    fun fantasyFontFamily(value: String) = this.apply { mFantasyFontFamily = value }
    fun fixedFontFamily(value: String) = this.apply { mFixedFontFamily = value }
    fun forceDark(value: Int) = this.apply { mForceDark = value }
    fun geolocationEnabled(value: Boolean) = this.apply { mGeolocationEnabled = value }
    fun javaScriptCanOpenWindowsAutomatically(value: Boolean) = this.apply { mJavaScriptCanOpenWindowsAutomatically = value }
    fun javaScriptEnabled(value: Boolean) = this.apply { mJavaScriptEnabled = value }
    fun layoutAlgorithm(value: WebSettings.LayoutAlgorithm) = this.apply { mLayoutAlgorithm = value }
    fun loadWithOverviewMode(value: Boolean) = this.apply { mLoadWithOverviewMode = value }
    fun loadsImagesAutomatically(value: Boolean) = this.apply { mLoadsImagesAutomatically = value }
    fun mediaPlaybackRequiresUserGesture(value: Boolean) = this.apply { mMediaPlaybackRequiresUserGesture = value }
    fun minimumFontSize(value: Int) = this.apply { mMinimumFontSize = value }
    fun minimumLogicalFontSize(value: Int) = this.apply { mMinimumLogicalFontSize = value }
    fun mixedContentMode(value: Int) = this.apply { mMixedContentMode = value }
    fun needInitialFocus(value: Boolean) = this.apply { mNeedInitialFocus = value }
    fun offscreenPreRaster(value: Boolean) = this.apply { mOffscreenPreRaster = value }
    fun safeBrowsingEnabled(value: Boolean) = this.apply { mSafeBrowsingEnabled = value }
    fun sansSerifFontFamily(value: String) = this.apply { mSansSerifFontFamily = value }
    fun serifFontFamily(value: String) = this.apply { mSerifFontFamily = value }
    fun standardFontFamily(value: String) = this.apply { mStandardFontFamily = value }
    fun supportMultipleWindows(value: Boolean) = this.apply { mSupportMultipleWindows = value }
    fun supportZoom(value: Boolean) = this.apply { mSupportZoom = value }
    fun textZoom(value: Int) = this.apply { mTextZoom = value }
    fun useWideViewPort(value: Boolean) = this.apply { mUseWideViewPort = value }
    fun userAgentString(value: String) = this.apply { mUserAgentString = value }
}
