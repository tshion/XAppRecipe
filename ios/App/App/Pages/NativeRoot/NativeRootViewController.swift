import MaterialComponents.MaterialButtons
import UIKit

class NativeRootViewController: UIViewController {
    static func newInstance() -> NativeRootViewController {
        return R.storyboard.nativeRoot.instantiateInitialViewController()!
    }

    @IBOutlet weak var buttonClose: MDCButton!
    @IBOutlet weak var buttonNext: UIButton!

    override func viewDidLoad() {
        super.viewDidLoad()
    }

    /**
     画面を閉じる
     */
    @IBAction func callClose(_ sender: Any) {
        dismiss(animated: true, completion: nil)
    }

    /// Top 画面へ遷移
    @IBAction func callTop(_ sender: Any) {
        let nextPage = TopViewController.newInstance()
        nextPage.modalPresentationStyle = .fullScreen
        present(nextPage, animated: true, completion: nil)
    }
}
