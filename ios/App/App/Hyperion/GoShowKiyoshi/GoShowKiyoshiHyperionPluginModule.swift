import HyperionCore

class GoShowKiyoshiPluginModule: GoViewControllerHyperionPluginModule {
    required init(with ext: HYPPluginExtensionProtocol) {
        super.init(with: ext) {
            ShowKiyoshiViewController.newInstance()
        }
    }

    override func pluginMenuItemTitle() -> String {
        return "ズンドコ節リストへ遷移"
    }
}
