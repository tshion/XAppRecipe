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
        guard let nowViewController = pluginView.window?.rootViewController else {
            return
        }

        let nextPage = NativeRootViewController.newInstance()
        nextPage.modalPresentationStyle = .fullScreen

        nowViewController.present(nextPage, animated: true, completion: nil)
    }
}
