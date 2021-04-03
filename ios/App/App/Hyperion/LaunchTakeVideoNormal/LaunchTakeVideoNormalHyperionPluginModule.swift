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

        let camera = UIImagePickerController.SourceType.camera
        guard UIImagePickerController.isSourceTypeAvailable(camera) else {
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

        let nextPage = UIImagePickerController()
        nextPage.mediaTypes = ["public.movie"]
        nextPage.sourceType = camera

        nextPage.modalPresentationStyle = .fullScreen
        nowViewController.present(nextPage, animated: true, completion: nil)
    }
}
