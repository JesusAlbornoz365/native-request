package com.parlevelsystems.plugins.nativerequest;

import androidx.annotation.NonNull;

import com.getcapacitor.JSObject;
import com.getcapacitor.PluginCall;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HttpUtils implements Callback {
    private PluginCall call;

    public HttpUtils(PluginCall call) {
        this.call = call;
    }

    @Override
    public void onFailure(@NonNull Call call, @NonNull IOException e) {
        this.call.reject(e.getMessage());
    }

    @Override
    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
        int code = response.code();
        boolean success = (code >= 200 && code < 300) || code == 401;
        if (!success) {
            this.call.reject(response.toString());
            return;
        }

        JSObject result = new JSObject();
        result.put("result", response.body().string());
        this.call.resolve(
            result
        );
    }
}
