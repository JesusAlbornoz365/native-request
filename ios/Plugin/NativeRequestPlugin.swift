import Foundation
import Capacitor
import Alamofire

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(NativeRequestPlugin)
public class NativeRequestPlugin: CAPPlugin {
    private let implementation = NativeRequest()
    
    @objc func formPost(_ call: CAPPluginCall) {
        
        let dataT = call.getObject("data") ?? [:];
        let headersT = call.getObject("headers") ?? [:];
        let url = call.getString("url", "");
        
        if url.isEmpty {
            call.reject("Empty URL");
            return;
        }
        
        var headers: HTTPHeaders = [];
        var data: [String: Any] = [:];
        
        for (key, value) in dataT {
            if (key == "sales") {
                var sales:[[String: Any]] = []
                for itemT in (value as? Array<Any> ?? []) {
                    let sitemT = itemT as! [String : Any]
                    let sale:[String:Any] = [
                        "price": sitemT["price"] as! Double,
                        "item": "\(sitemT["item"]!)",
                        "quantity": sitemT["quantity"] as! Int,
                        "tax": sitemT["tax"] as! Double,
                        "name": "\(sitemT["name"]!)"
                    ]
                   sales.append(sale)
               }
                
                data[key] = sales;
                
            } else if (key == "paid") {
                data[key] = ["wallet": (value as? Double)]
            } else if (key == "items") {
                var items:[[String: Any]] = []
                
                for itemP in (value as? Array<Any> ?? []) {
                    let sitemT = itemP as! [String : Any]
                    let itemPromo:[String:Any] = [
                        "id": sitemT["id"] as! String,
                        "name": "\(sitemT["name"]!)",
                        "quantity": sitemT["quantity"] as! Int,
                        "price": sitemT["price"] as! Double,
                        "tags": sitemT["tags"] as! Array<String>,
                        "tax": sitemT["tax"] as! Double,
                    ]
                    items.append(itemPromo)
               }
                
                data[key] = items;
            } else if (key == "cart") {
                var items:[[String: Any]] = []
                
                for itemP in (value as? Array<Any> ?? []) {
                    let sitemT = itemP as! [String : Any]
                    let promo = sitemT["promotion"] as? [String: Any]
                    if (promo?.count == 0) {
                        let itemPromo:[String:Any] = [
                            "id": sitemT["id"] as! String,
                            "quantity": sitemT["quantity"] as! Int,
                            "price": sitemT["price"] as! Double,
                            "tags": sitemT["tags"] as! Array<String>,
                            "name": sitemT["name"] as! String,
                        ]
                        items.append(itemPromo)
                    } else {
                        
                        let promoObject: [String: Any] = [
                            "id": promo!["id"] as! String,
                            "promotionId": promo?["promotionId"] as! String,
                            "notification": promo?["notification"] as! String,
                            "price": promo?["price"] as! [String: Double],
                        ];
                        let itemPromo:[String:Any] = [
                            "id": sitemT["id"] as! String,
                            "quantity": sitemT["quantity"] as! Int,
                            "price": sitemT["price"] as! Double,
                            "name": sitemT["name"] as! String,
                            "tags": sitemT["tags"] as! Array<String>,
                            "promotion": promoObject
                        ]
                        items.append(itemPromo)
                    }
                }
                data[key] = items;
            } else {
                data[key] = value as? String;
            }
        }
        
        for (key, value) in headersT {
            headers[key] = value as? String;
        }
        
        let request = implementation.post(
            url,
            data: data,
            headers: headers);
        
        request.validate()
            .responseString { response in
                let jsObject = [
                    "result": response.value
                ]
                call.resolve(jsObject as PluginCallResultData);
            }
         
    }
    
    @objc func get(_ call: CAPPluginCall) {
        let headersT = call.getObject("headers") ?? [:];
        let url = call.getString("url", "");
        
        if url.isEmpty {
            call.reject("Empty URL");
            return;
        }
        
        var headers: HTTPHeaders = [];
        
        for (key, value) in headersT {
            headers[key] = value as? String;
        }
        
        let request = implementation.get(
            url,
            headers: headers);
        
        request.validate()
            .responseString { response in
                let jsObject = [
                    "result": response.value
                ];
                call.resolve(jsObject as PluginCallResultData);
            }
        
        
    }

    @objc func post(_ call: CAPPluginCall) {
        let dataT = call.getObject("data") ?? [:];
        let headersT = call.getObject("headers") ?? [:];
        let url = call.getString("url", "");

        var headers: HTTPHeaders = [];


        for (key, value) in headersT {
            headers[key] = value as? String;
        }
        
        let request = implementation.post(
            url,
            data: dataT,
            headers: headers);
        
        request.validate()
            .responseString { response in
                let jsObject = [
                    "result": response.value
                ]
                call.resolve(jsObject as PluginCallResultData);
            }
    }
}
