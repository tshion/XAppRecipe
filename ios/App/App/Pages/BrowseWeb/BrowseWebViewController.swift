import WebKit
import mktools_ios

/// アプリ内WEB 表示
class BrowseWebViewController: UIViewController {
    @IBOutlet weak var browser: WKWebView!

    static func newInstance() -> BrowseWebViewController {
        return BrowseWebViewController(nib: R.nib.browseWebViewController)
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        browser.navigationDelegate = self
        if let url = URL(string: "https://www.google.co.jp/maps") {
            let request = URLRequest(url: url)
            browser.load(request)
        }
    }
}

extension BrowseWebViewController: WKNavigationDelegate {
    func webView(
        _ webView: WKWebView, didFailProvisionalNavigation navigation: WKNavigation!,
        withError: Error
    ) {
        let alert = AlertBuilder()
            .addAction(UIAlertAction(title: "OK", style: .default))
            .message(withError.localizedDescription)
            .title("通信エラー")
            .create()
        present(alert, animated: true, completion: nil)
    }
}
