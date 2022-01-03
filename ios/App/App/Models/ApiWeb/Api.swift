import Alamofire
import Combine

class Api {
    func getHtml(
        path: String
    ) -> DataResponsePublisher<String> {
        return AF.request(path)
            .publishString()
    }
}
