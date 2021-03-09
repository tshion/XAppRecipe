import MaterialComponents.MaterialButtons
import UIKit

class NativeRootViewController: UIViewController {
    @IBOutlet weak var buttonClose: MDCButton!

    static func newInstance() -> NativeRootViewController {
        let storyboard = UIStoryboard(name: "NativeRoot", bundle: nil)
        let vc = storyboard.instantiateViewController(withIdentifier: "NativeRootViewController") as? NativeRootViewController
        return vc!
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
