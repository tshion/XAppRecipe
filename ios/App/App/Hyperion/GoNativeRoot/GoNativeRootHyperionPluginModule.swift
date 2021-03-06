import Foundation
import HyperionCore

class GoNativeRootHyperionPluginModule: HYPPluginModule {
    private var displayingViewController: UIViewController?

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

        let buttonClose = UIButton(type: UIButton.ButtonType.detailDisclosure)
        buttonClose.addTarget(self, action: #selector(tappedButtonClose(_:)), for: UIControl.Event.touchUpInside)
        buttonClose.backgroundColor = UIColor.systemOrange
        buttonClose.sizeToFit()

        let nextPage = NativeRootViewController.newInstance()
               
        buttonClose.center = CGPoint(x: 16, y: nextPage.view.center.y)
        nextPage.view.addSubview(buttonClose)

        nextPage.modalPresentationStyle = .fullScreen
        nowViewController.present(nextPage, animated: true, completion: nil)
        displayingViewController = nextPage
    }

    @objc func tappedButtonClose(_ sender: UIButton) {
        displayingViewController?.dismiss(animated: true, completion: nil)
        displayingViewController = nil
    }
}
