import UIKit

class KiyoshiViewCell: UICollectionViewCell {
    private static let fontSize = UIFont.systemFont(ofSize: 40.0)

    @IBOutlet weak var message: UILabel!

    override func awakeFromNib() {
        super.awakeFromNib()

        message.font = KiyoshiViewCell.fontSize
        message.numberOfLines = 3
    }

    /**
     表示の高さ算出
     */
    func calculateHeight(data: String) -> CGFloat {
        let attribute = [NSAttributedString.Key.font: KiyoshiViewCell.fontSize]
        let maxSize = CGSize(width: message.bounds.size.width, height: CGFloat.greatestFiniteMagnitude)

        let rect = (data as NSString).boundingRect(with: maxSize, options: .usesLineFragmentOrigin, attributes: attribute, context: nil)
        return ceil(rect.size.height) + 40
    }

    /**
     表示内容の更新
     */
    func update(data: String) {
        message.text = data
    }
}
