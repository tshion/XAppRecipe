import HyperionCore

/**
 標準のビデオ撮影機能の呼び出し実装
 */
class LaunchTakeVideoNormalHyperionPluginModule: HYPPluginModule {
    override func pluginMenuItemTitle() -> String {
        return "標準のビデオ撮影機能の呼び出し"
    }
}

extension LaunchTakeVideoNormalHyperionPluginModule: HYPPluginMenuItemDelegate {
    func pluginMenuItemSelected(_ pluginView: (UIView & HYPPluginMenuItemProtocol)!) {
        guard let nowViewController = pluginView.window?.rootViewController else {
            return
        }

        let builder = UIImagePickerControllerBuilder()
            .appendMediaFilterMovie()
            .setSourceType(UIImagePickerController.SourceType.camera)

        guard let picker = builder.build() else {
            let dismiss = UIAlertAction(
                title: "OK",
                style: .default,
                handler: nil
            )

            let alert = UIAlertController(
                title: "エラー",
                message: "カメラ機能は使えません",
                preferredStyle: UIAlertController.Style.alert
            )
            alert.addAction(dismiss)

            nowViewController.present(alert, animated: true, completion: nil)
            return
        }
        picker.modalPresentationStyle = .fullScreen

        nowViewController.present(picker, animated: true, completion: nil)
    }
}
