import Foundation
import HyperionCore

class GoNativeRootHyperionPluginModule: HYPPluginModule {
    required init(with ext: HYPPluginExtensionProtocol) {
        super.init(with: ext)
    }

    override func pluginMenuItemTitle() -> String {
        return "Native 実装のルートへ遷移"
    }
}

extension GoNativeRootHyperionPluginModule: HYPPluginMenuItemDelegate {
    func pluginMenuItemSelected(_ pluginView: (UIView & HYPPluginMenuItemProtocol)!) {
        guard let nowViewController: UIViewController = pluginView.findFirstViewOrNil() else {
            return
        }

        let storyboard = UIStoryboard(name: "NativeRoot", bundle: nil)
        guard let nextPage = storyboard.instantiateViewController(withIdentifier: "NativeRootViewController") as? NativeRootViewController else {
            return
        }
        nextPage.modalPresentationStyle = .fullScreen

        nowViewController.present(nextPage, animated: true, completion: nil)
    }
}
