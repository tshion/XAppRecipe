import UIKit

class ShowKiyoshiViewController: UIViewController {
    private static let cellId = "KiyoshiViewCell"

    private var displayData: [String]?
    @IBOutlet weak var list: UICollectionView!
    private let model = ShowKiyoshiModel()

    static func newInstance() -> ShowKiyoshiViewController {
        return ShowKiyoshiViewController(nibName: "ShowKiyoshiViewController", bundle: nil)
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        displayData = [String](repeating: "", count: 30)
            .map { _ in model.generate() }

        list.dataSource = self
        list.delegate = self
        list.register(KiyoshiViewCell.newInstance(), forCellWithReuseIdentifier: ShowKiyoshiViewController.cellId)
    }
}

extension ShowKiyoshiViewController: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: ShowKiyoshiViewController.cellId, for: indexPath)

        if let cell = cell as? KiyoshiViewCell, let item = displayData?[indexPath.row] {
            cell.update(data: item)
        }

        return cell
    }

    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return displayData?.count ?? 0
    }

    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }
}

extension ShowKiyoshiViewController: UICollectionViewDelegate {}

extension ShowKiyoshiViewController: UICollectionViewDelegateFlowLayout {
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: ShowKiyoshiViewController.cellId, for: indexPath)
        let item = displayData?[indexPath.row]

        switch cell {
        case let target as KiyoshiViewCell:
            return CGSize(width: collectionView.frame.width, height: target.calculateHeight(data: item ?? ""))

        default:
            return CGSize(width: 0, height: 0)
        }
    }
}
