import UIKit

/**
 アプリ内WEB 表示
 */
class BrowseWebViewController: UIViewController {
    static func newInstance() -> BrowseWebViewController {
        return BrowseWebViewController(nibName: "BrowseWebViewController", bundle: nil)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
    }
}
