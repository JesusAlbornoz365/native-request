import Foundation
import Alamofire
import Capacitor

@objc public class NativeRequest: NSObject {
    
    fileprivate static var staticManager: Session?
    
    static var manager: Session {
        if staticManager != nil {
            return staticManager!
        }
        
        let configuration: URLSessionConfiguration = URLSessionConfiguration.default
        staticManager = Alamofire.Session(
            configuration: configuration
        )
        return staticManager!
    }
    
    open func post(_ url: String, data: [String: Any], headers: HTTPHeaders) -> DataRequest{
        return NativeRequest.manager.request(url, method: .post, parameters: data, encoding: JSONEncoding.default, headers: headers);
    }
    
    open func get(_ url: String, headers: HTTPHeaders) -> DataRequest{
        return NativeRequest.manager.request(url, method: .get, headers: headers);
    }
}

