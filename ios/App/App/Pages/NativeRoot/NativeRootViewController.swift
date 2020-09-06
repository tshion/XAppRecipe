import UIKit

class NativeRootViewController: UIViewController {
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
