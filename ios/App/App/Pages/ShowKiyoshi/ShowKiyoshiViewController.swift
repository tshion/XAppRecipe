import UIKit

class ShowKiyoshiViewController: UIViewController {
    private var adapter: ShowKiyoshiAdapter?
    @IBOutlet weak var list: UICollectionView!
    private let model = ShowKiyoshiModel()

    static func newInstance() -> ShowKiyoshiViewController {
        return ShowKiyoshiViewController(nib: R.nib.showKiyoshiViewController)
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        adapter = ShowKiyoshiAdapter()
        adapter?.with(target: list)

        var groupId = 0
        Timer.scheduledTimer(withTimeInterval: 1.5, repeats: true) { _ in
            guard groupId < 3 else { return }

            self.adapter?.displayData = [String](repeating: "", count: 30)
                .map { _ in self.model.generate(groupId) }
            groupId += 1
        }
    }
}
