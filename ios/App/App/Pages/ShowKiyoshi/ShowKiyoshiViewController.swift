import UIKit

class ShowKiyoshiViewController: UIViewController {
    @IBOutlet weak var list: UICollectionView!

    static func newInstance() -> ShowKiyoshiViewController {
        return ShowKiyoshiViewController(nibName: "ShowKiyoshiViewController", bundle: nil)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
    }
}
