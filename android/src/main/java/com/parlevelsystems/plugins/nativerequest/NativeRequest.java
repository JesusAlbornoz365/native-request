package com.parlevelsystems.plugins.nativerequest;
import java.util.Map;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;


public class NativeRequest {

    public Request formPost(String url, Map<String, String> data, Map<String, String> headers) {

        FormBody.Builder builder = new FormBody.Builder();

        for(Map.Entry<String, String> entry : data.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }

        RequestBody requestBody = builder.build();

        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .post(requestBody);

        for(Map.Entry<String, String> entry : headers.entrySet()) {
            requestBuilder.addHeader(entry.getKey(), entry.getValue());
        }

        return requestBuilder.build();

    }

    public Request jsonRequest(String url, String data, Map<String, String> headers) {
        MediaType json = MediaType.parse("application/json; charset=utf-8");

        RequestBody requestBody = RequestBody.create(data, json);

        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .post(requestBody);

        for(Map.Entry<String, String> entry : headers.entrySet()) {
            requestBuilder.addHeader(entry.getKey(), entry.getValue());
        }

        return requestBuilder.build();
    }

    public Request get(String url, Map<String, String> headers) {
        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .get();

        for(Map.Entry<String, String> entry : headers.entrySet()) {
            requestBuilder.addHeader(entry.getKey(), entry.getValue());
        }

        return requestBuilder.build();
    }
}
