import HyperionCore

class GoBrowseWebPluginModule: GoViewControllerHyperionPluginModule {
    required init(with ext: HYPPluginExtensionProtocol) {
        super.init(with: ext) {
            BrowseWebViewController.newInstance()
        }
    }

    override func pluginMenuItemTitle() -> String {
        return "WEB 表示へ遷移"
    }
}
