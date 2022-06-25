import HyperionCore

/// 標準のビデオ撮影機能の呼び出し実装
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
            .setDelegate(self)
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

extension LaunchTakeVideoNormalHyperionPluginModule: UIImagePickerControllerDelegate
        & UINavigationControllerDelegate
{
    func imagePickerController(
        _ picker: UIImagePickerController,
        didFinishPickingMediaWithInfo info: [UIImagePickerController.InfoKey: Any]
    ) {
        guard let fileUrl = info[.mediaURL] as? URL else {
            return
        }

        let documentsDirectoryURL = FileManager.default.urls(
            for: .documentDirectory, in: .userDomainMask
        ).first!
        do {
            try FileManager.default.moveItem(
                at: fileUrl,
                to: documentsDirectoryURL.appendingPathComponent(fileUrl.lastPathComponent))
            print("動画の保存に成功しました。")
        } catch {
            print("動画の保存に失敗しました。")
        }

        picker.dismiss(animated: true, completion: nil)
    }
}
