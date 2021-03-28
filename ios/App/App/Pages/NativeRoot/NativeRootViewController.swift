import MaterialComponents.MaterialButtons
import UIKit

class NativeRootViewController: UIViewController {
    @IBOutlet weak var buttonClose: MDCButton!

    static func newInstance() -> NativeRootViewController {
        return R.storyboard.nativeRoot.instantiateInitialViewController()!
    }

    override func viewDidLoad() {
        super.viewDidLoad()
    }

    /**
     画面を閉じる
     */
    @IBAction func callClose(_ sender: Any) {
        dismiss(animated: true, completion: nil)
    }
}
