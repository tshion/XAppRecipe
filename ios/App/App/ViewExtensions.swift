import UIKit

extension UIApplication {
    
    /**
     最前面にあるViewController の検索
     - Note
        ```
        let vc = UIApplication.shared.findForegroundViewController()
        ```
     - Precondition
        ```import UIKit```
     - Returns: 最前面にあるViewController or nil
     */
    func findForegroundViewController() -> UIViewController? {
        var result = Self.shared.keyWindow?.rootViewController
        while let candidate = result?.presentedViewController {
            result = candidate
        }
        return result
    }
}
