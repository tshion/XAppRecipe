import UIKit

extension UIResponder {
    /**
     所属するView Tree 内で、指定する型に一致する最初のものを取得
     - Note
        ```
        let vc: UIViewController = view.findFirstViewOrNil()
        ```
     - Returns: 指定する型に一致する最初のもの or nil
     */
    func findFirstViewOrNil<T: UIResponder>() -> T? {
        var responder = self
        while let candidate = responder.next {
            if let result = candidate as? T {
                return result
            }
            responder = candidate
        }
        return nil
    }
}
