package com.parlevelsystems.plugins.nativerequest;

import com.getcapacitor.JSObject;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

public class JsonGenerator {

    public static String generateTransactionJson(JSObject data) {
        try {
            JSONArray jsonSales = new JSONArray();

            var jsonArray = data.getJSONArray("sales");

            for (int i = 0; i < jsonArray.length(); i++) {

                var item = jsonArray.getJSONObject(i);
                JSONObject jsonProduct = new JSONObject();
                jsonProduct.put("price", item.getDouble("price"));
                jsonProduct.put("item", item.getString("item"));
                jsonProduct.put("quantity", item.getInt("quantity"));
                jsonProduct.put("tax", item.getDouble("tax"));
                jsonProduct.put("name", item.getString("name"));
                jsonSales.put(jsonProduct);
            }


            JSONArray cartData = generatePromoJsonObject(data,"cart");

            JSONObject jsonPaid = new JSONObject();
            jsonPaid.put("wallet", data.getDouble("paid"));

            JSONObject jsonData = new JSONObject();
            jsonData.put("token", "");
            jsonData.put("deviceId", data.getString("deviceId"));
            jsonData.put("userId", data.getString("userId"));
            jsonData.put("customer", data.getString("customer"));
            jsonData.put("type", "1");
            jsonData.put("status", "1");
            jsonData.put("source", "4");
            jsonData.put("sales", jsonSales);
            jsonData.put("paid", jsonPaid);

            jsonData.put("amount_redemed", data.getDouble("amount_redemed"));
            jsonData.put("dateTime", data.getString("dateTime"));
            jsonData.put("payMethod", data.getString("payMethod"));
            jsonData.put("location", data.getString("location"));
            jsonData.put("cart", cartData);

            String jsonString = jsonData.toString();
            return jsonString;
        } catch (JSONException e) {
            return "";
        }
    }

    public static String generatePromoJson(JSONObject data, String key) {
        try {
            JSONArray jsonItems = new JSONArray();

            var jsonArray = data.getJSONArray(key);

            for (int i = 0; i < jsonArray.length(); i++) {

                var item = jsonArray.getJSONObject(i);
                JSONObject jsonProduct = new JSONObject();
                jsonProduct.put("price", item.getDouble("price"));
                jsonProduct.put("id", item.getString("id"));
                jsonProduct.put("quantity", item.getInt("quantity"));
                jsonProduct.put("name", item.getString("name"));
                jsonProduct.put("tax", item.getDouble("tax"));

                // check for promo object
                var promo = item.getJSONObject("promotion");

                if (promo.length() > 0) {
                    var priceObject = promo.getJSONObject("price");
                    JSONObject promoItem = new JSONObject();
                    JSONObject priceItem = new JSONObject();

                    priceItem.put("after", priceObject.getDouble("after"));
                    priceItem.put("before", priceObject.getDouble("before"));

                    promoItem.put("id", promo.getString("id"));
                    promoItem.put("promotionId", promo.getString("promotionId"));
                    promoItem.put("notification", promo.getString("notification"));
                    promoItem.put("price", priceItem);

                    jsonProduct.put("promotion", promoItem);
                }

                var jsonTagsArray = item.getJSONArray("tags");
                jsonProduct.put("tags", jsonTagsArray);
                jsonItems.put(jsonProduct);
            }

            JSONObject jsonData = new JSONObject();
            jsonData.put(key, jsonItems);

            jsonData.put("kiosk_id", data.getString("kiosk_id"));

            if (key.equals("cart")) {
                 jsonData.put("dateTime", data.getString("dateTime"));
                 jsonData.put("payMethod", data.getString("payMethod"));
                 jsonData.put("location", data.getString("location"));
            }

            String jsonString = jsonData.toString();
            return jsonString;
        } catch (JSONException e) {
            return "";
        }
    }

    private static JSONArray generatePromoJsonObject(JSONObject data, String key) {
        try {
            JSONArray jsonItems = new JSONArray();

            var jsonArray = data.getJSONArray(key);

            for (int i = 0; i < jsonArray.length(); i++) {

                var item = jsonArray.getJSONObject(i);
                JSONObject jsonProduct = new JSONObject();
                jsonProduct.put("price", item.getDouble("price"));
                jsonProduct.put("id", item.getString("id"));
                jsonProduct.put("quantity", item.getInt("quantity"));
                jsonProduct.put("name", item.getString("name"));
                jsonProduct.put("tax", item.getDouble("tax"));

                // check for promo object
                var promo = item.getJSONObject("promotion");

                if (promo.length() > 0) {
                    var priceObject = promo.getJSONObject("price");
                    JSONObject promoItem = new JSONObject();
                    JSONObject priceItem = new JSONObject();

                    priceItem.put("after", priceObject.getDouble("after"));
                    priceItem.put("before", priceObject.getDouble("before"));

                    promoItem.put("id", promo.getString("id"));
                    promoItem.put("promotionId", promo.getString("promotionId"));
                    promoItem.put("notification", promo.getString("notification"));
                    promoItem.put("price", priceItem);

                    jsonProduct.put("promotion", promoItem);
                }

                var jsonTagsArray = item.getJSONArray("tags");
                jsonProduct.put("tags", jsonTagsArray);
                jsonItems.put(jsonProduct);
            }


            return jsonItems;

        } catch (JSONException e) {
            return new JSONArray();
        }
    }
}
