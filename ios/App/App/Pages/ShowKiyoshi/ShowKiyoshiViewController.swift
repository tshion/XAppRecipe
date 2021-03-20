import UIKit

class ShowKiyoshiViewController: UIViewController {
    private var adapter: ShowKiyoshiAdapter?
    @IBOutlet weak var list: UICollectionView!
    private let model = ShowKiyoshiModel()

    static func newInstance() -> ShowKiyoshiViewController {
        return ShowKiyoshiViewController(nibName: "ShowKiyoshiViewController", bundle: nil)
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        adapter = ShowKiyoshiAdapter()
        adapter?.with(target: list)

        Timer.scheduledTimer(withTimeInterval: 1.5, repeats: false) { _ in
            self.adapter?.displayData = [String](repeating: "", count: 30)
                .map { _ in self.model.generate() }
        }
    }
}
