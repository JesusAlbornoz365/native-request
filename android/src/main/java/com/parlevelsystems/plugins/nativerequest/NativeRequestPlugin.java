package com.parlevelsystems.plugins.nativerequest;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;


@CapacitorPlugin(name = "NativeRequest")
public class NativeRequestPlugin extends Plugin {

    private final NativeRequest implementation = new NativeRequest();

    private static final OkHttpClient client;

    static {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS);

        client = clientBuilder.build();
    }


    @PluginMethod
    public void formPost(PluginCall call) {

        JSObject data = call.getObject("data");
        JSObject headers = call.getObject("headers");

        Map<String, String> dataPass = new HashMap<>();
        Map<String, String> headersPass = new HashMap<>();

        var isSale = false;
        var isPromo = false;
        // var isPromoPurchase = false;

        for (Iterator<String> i = data.keys(); i.hasNext();) {
            String item = i.next();

            switch (item) {
                case "sales":
                case "paid":
                    isSale = true;
                    break;
                case "items":
                    isPromo = true;
                    break;
                default:
                    dataPass.put(item, data.getString(item));
                    break;
            }
        }

        for (Iterator<String> i = headers.keys(); i.hasNext();) {
            String item = i.next();
            headersPass.put(item, headers.getString(item));
        }

        if (isSale) {
            String jsonGenerated = JsonGenerator.generateTransactionJson(data);
            if (jsonGenerated.isEmpty()) {
                call.reject("Empty transaction data");
                return;
            }

            Request requestJson = implementation.jsonRequest(
                    call.getString("url"),
                    jsonGenerated,
                    headersPass
            );

            client.newCall(requestJson).enqueue(
                new HttpUtils(call)
            );

        }
        else if (isPromo) {
            String promoJsonGenerated = JsonGenerator.generatePromoJson(data, "items");
           if (promoJsonGenerated.isEmpty()) {
                call.reject("Empty promo data");
                return;
            }

            Request requestJson = implementation.jsonRequest(
                call.getString("url"),
                promoJsonGenerated,
                headersPass
            );

            client.newCall(requestJson).enqueue(
                new HttpUtils(call)
            );

        }
        else {
            Request request = implementation.formPost(
                call.getString("url"),
                dataPass,
                headersPass
            );

            client.newCall(request).enqueue(
                new HttpUtils(call)
            );

        }
    }

    @PluginMethod
    public void get(PluginCall call) {
        JSObject headers = call.getObject("headers");

        Map<String, String> headersPass = new HashMap<>();

        for (Iterator<String> i = headers.keys(); i.hasNext();) {
            String item = i.next();
            headersPass.put(item, headers.getString(item));
        }

        Request request = implementation.get(
            call.getString("url"),
            headersPass
        );

        client.newCall(request).enqueue(
            new HttpUtils(call)
        );
    }

    @PluginMethod
    public void post(PluginCall call) {
        JSObject headers = call.getObject("headers");

        Map<String, String> headersPass = new HashMap<>();

        for (Iterator<String> i = headers.keys(); i.hasNext();) {
            String item = i.next();
            headersPass.put(item, headers.getString(item));
        }

        JSObject data = call.getObject("data");

        Request requestJson = implementation.jsonRequest(
            call.getString("url"),
            data.toString(),
            headersPass
        );

        client.newCall(requestJson).enqueue(
                new HttpUtils(call)
        );
    }
}
