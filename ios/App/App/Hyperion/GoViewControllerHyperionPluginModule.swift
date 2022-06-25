import HyperionCore

/**
 任意のViewController に遷移するHyperion Plugin Module
 - Note
    継承先のイニシャライザーで```init(with:_:)``` を呼び出してください
    ``` swift
    class Sample: GoViewControllerHyperionPluginModule {
        required init(with ext: HYPPluginExtensionProtocol) {
            super.init(with: ext) {
                code
            }
        }
    }
    ```
 */
class GoViewControllerHyperionPluginModule: HYPPluginModule {
    private var displayingViewController: UIViewController?
    private let targetGenerator: () -> UIViewController

    init(with ext: HYPPluginExtensionProtocol, _ generator: @escaping () -> UIViewController) {
        targetGenerator = generator
        super.init(with: ext)
    }

    required init(with extension: HYPPluginExtensionProtocol) {
        fatalError("init(with:) has not been implemented")
    }
}

extension GoViewControllerHyperionPluginModule: HYPPluginMenuItemDelegate {
    func pluginMenuItemSelected(_ pluginView: (UIView & HYPPluginMenuItemProtocol)!) {
        guard let nowViewController = pluginView.window?.rootViewController else {
            return
        }

        let buttonClose = UIButton(type: UIButton.ButtonType.detailDisclosure)
        buttonClose.addTarget(self, action: #selector(tappedButtonClose(_:)), for: UIControl.Event.touchUpInside)
        buttonClose.backgroundColor = UIColor.systemOrange
        buttonClose.sizeToFit()

        let nextPage = targetGenerator()

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
