import Foundation
import HyperionCore

class ShowNativeRootHyperionPluginModule: HYPPluginModule {
    required init(with ext: HYPPluginExtensionProtocol) {
        super.init(with: ext)
    }

    override func pluginMenuItemTitle() -> String {
        return "Native 実装のルートへ遷移"
    }
}

extension ShowNativeRootHyperionPluginModule: HYPPluginMenuItemDelegate {
    func pluginMenuItemSelected(_ pluginView: (UIView & HYPPluginMenuItemProtocol)!) {
        guard let nowViewController = getForegroundViewController() else {
            return
        }

        let storyboard = UIStoryboard(name: "NativeRoot", bundle: nil)
        guard let nextPage = storyboard.instantiateViewController(withIdentifier: "NativeRootViewController") as? NativeRootViewController else {
            return
        }
        nextPage.modalPresentationStyle = .fullScreen

        nowViewController.present(nextPage, animated: true, completion: nil)
    }

    /*
     [現在表示されているViewControllerを取得する方法](https://qiita.com/maebaru/items/ae954b19ad334f990538)
     */
    private func getForegroundViewController() -> UIViewController? {
        if let root = UIApplication.shared.keyWindow?.rootViewController {
            var vc = root
            while let candidate = vc.presentedViewController {
                vc = candidate
            }
            return vc
        } else {
            return nil
        }
    }
}
