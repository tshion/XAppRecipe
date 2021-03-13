import WebKit

/**
 アプリ内WEB 表示
 */
class BrowseWebViewController: UIViewController {
    @IBOutlet weak var browser: WKWebView!

    static func newInstance() -> BrowseWebViewController {
        return BrowseWebViewController(nibName: "BrowseWebViewController", bundle: nil)
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        if let url = URL(string: "https://www.google.co.jp/maps") {
            let request = URLRequest(url: url)
            browser.load(request)
        }
    }
}
