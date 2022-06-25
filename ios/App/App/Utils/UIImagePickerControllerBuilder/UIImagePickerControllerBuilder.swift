import UIKit

/// UIImagePickerController に設定を追加してからインスタンス生成するためのビルダー
class UIImagePickerControllerBuilder {
    typealias PickDelegate = (UIImagePickerControllerDelegate & UINavigationControllerDelegate)

    private static let mediaMovie = "public.movie"

    private weak var _delegate: PickDelegate?
    public weak var delegate: PickDelegate? {
        return _delegate
    }

    public private(set) var mediaTypes = Set<String>()
    public private(set) var sourceType: UIImagePickerController.SourceType?

    func appendMediaFilterMovie() -> UIImagePickerControllerBuilder {
        mediaTypes.insert(UIImagePickerControllerBuilder.mediaMovie)
        return self
    }

    func build() -> UIImagePickerController? {
        guard let type = sourceType, UIImagePickerController.isSourceTypeAvailable(type) else {
            return nil
        }

        let vc = UIImagePickerController()
        vc.delegate = delegate
        if !mediaTypes.isEmpty {
            vc.mediaTypes = Array(mediaTypes)
        }
        vc.sourceType = type
        return vc
    }

    func clearMediaFilter() -> UIImagePickerControllerBuilder {
        mediaTypes.removeAll()
        return self
    }

    func removeMediaFilterMovie() -> UIImagePickerControllerBuilder {
        mediaTypes.remove(UIImagePickerControllerBuilder.mediaMovie)
        return self
    }

    func setDelegate(_ value: PickDelegate?) -> UIImagePickerControllerBuilder {
        _delegate = value
        return self
    }

    func setSourceType(_ value: UIImagePickerController.SourceType?)
        -> UIImagePickerControllerBuilder
    {
        sourceType = value
        return self
    }
}
