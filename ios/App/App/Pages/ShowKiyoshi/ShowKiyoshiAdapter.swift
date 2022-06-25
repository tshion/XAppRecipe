import UIKit

/// 繰り返し表示周りの管理
class ShowKiyoshiAdapter: NSObject {
    var displayData: [String]? {
        didSet {
            list?.reloadData()
        }
    }

    private weak var list: UICollectionView?

    func with(target: UICollectionView) {
        target.dataSource = self
        target.delegate = self
        target.register(R.nib.kiyoshiViewCell)
        list = target
    }
}

extension ShowKiyoshiAdapter: UICollectionViewDataSource {
    func collectionView(
        _ collectionView: UICollectionView,
        cellForItemAt indexPath: IndexPath
    ) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(
            withReuseIdentifier: R.reuseIdentifier.kiyoshiViewCell, for: indexPath)!

        if let item = displayData?[indexPath.row] {
            cell.update(data: item)
        }

        if indexPath.row % 2 == 0 {
            cell.backgroundColor = .cyan
        } else {
            cell.backgroundColor = .none
        }

        return cell
    }

    func collectionView(
        _ collectionView: UICollectionView,
        numberOfItemsInSection section: Int
    ) -> Int {
        return displayData?.count ?? 0
    }

    func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 1
    }
}

extension ShowKiyoshiAdapter: UICollectionViewDelegate {}

extension ShowKiyoshiAdapter: UICollectionViewDelegateFlowLayout {
    func collectionView(
        _ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout,
        sizeForItemAt indexPath: IndexPath
    ) -> CGSize {
        let cell = collectionView.dequeueReusableCell(
            withReuseIdentifier: R.reuseIdentifier.kiyoshiViewCell, for: indexPath)
        let item = displayData?[indexPath.row]

        switch cell {
        case let target as KiyoshiViewCell:
            return CGSize(
                width: collectionView.frame.width, height: target.calculateHeight(data: item ?? ""))

        default:
            return CGSize(width: 0, height: 0)
        }
    }
}
