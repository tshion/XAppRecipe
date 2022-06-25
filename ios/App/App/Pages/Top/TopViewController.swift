import MaterialComponents.MaterialBottomNavigation
import UIKit

class TopViewController: UIViewController {
    static func newInstance() -> TopViewController {
        return TopViewController(nib: R.nib.topViewController)
    }

    @IBOutlet weak var footer: MDCBottomNavigationBar!
    @IBOutlet weak var root: UIView!
    private let tabFavorite = (
        tab: UITabBarItem(
            title: "Favorites",
            image: R.image.icon_iphone()?.resize(size: CGSize(width: 24, height: 24)),
            tag: 2
        ),
        vc: ShowKiyoshiViewController.newInstance()
    )
    private let tabHome = (
        tab: UITabBarItem(
            title: "Home",
            image: R.image.icon_iphone()?.resize(size: CGSize(width: 24, height: 24)),
            tag: 0
        ),
        vc: ShowKiyoshiViewController.newInstance()
    )
    private let tabMessage = (
        tab: UITabBarItem(
            title: "Messages",
            image: R.image.icon_iphone()?.resize(size: CGSize(width: 24, height: 24)),
            tag: 1
        ),
        vc: BrowseWebViewController.newInstance()
    )

    override func viewDidLoad() {
        super.viewDidLoad()

        tabMessage.tab.badgeValue = "8"
        tabFavorite.tab.badgeValue = ""

        footer.alignment = MDCBottomNavigationBarAlignment.justifiedAdjacentTitles
        footer.backgroundColor = R.color.app_main()
        footer.delegate = self
        footer.elevation = ShadowElevation.none
        footer.items = [
            tabHome.tab,
            tabMessage.tab,
            tabFavorite.tab,
        ]
        footer.selectedItem = tabMessage.tab
        footer.selectedItemTintColor = UIColor.white
        footer.titleVisibility = MDCBottomNavigationBarTitleVisibility.always

        showTabContent(content: tabMessage.vc)
    }

    func hideTabContent(content: UIViewController) {
        content.willMove(toParent: nil)
        content.view.removeFromSuperview()
        content.removeFromParent()
    }

    func showTabContent(content: UIViewController) {
        addChild(content)
        content.view.frame = root.frame
        root.addSubview(content.view)
        content.didMove(toParent: self)
    }
}

extension TopViewController: MDCBottomNavigationBarDelegate {
    func bottomNavigationBar(_ bottomNavigationBar: MDCBottomNavigationBar, didSelect item: UITabBarItem) {
        switch item.tag {
        case 0:
            hideTabContent(content: tabFavorite.vc)
            showTabContent(content: tabHome.vc)
            hideTabContent(content: tabMessage.vc)
        case 1:
            hideTabContent(content: tabFavorite.vc)
            hideTabContent(content: tabHome.vc)
            showTabContent(content: tabMessage.vc)
        default:
            showTabContent(content: tabFavorite.vc)
            hideTabContent(content: tabHome.vc)
            hideTabContent(content: tabMessage.vc)
        }
    }
}
