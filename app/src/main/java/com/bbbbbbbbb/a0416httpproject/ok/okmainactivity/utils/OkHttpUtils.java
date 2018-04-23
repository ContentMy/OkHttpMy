package com.bbbbbbbbb.a0416httpproject.ok.okmainactivity.utils;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by ld on 2018/4/19.
 */
/*
*   依赖compile 'com.squareup.okhttp3:okhttp:3.10.0'
*/
public class OkHttpUtils {
    private final OkHttpClient mClient;
    private static OkHttpUtils mInstance;

    private OkHttpUtils() {
        mClient = new OkHttpClient();

    }

    public static OkHttpUtils getInstance() {
        if (mInstance == null) {
            synchronized (OkHttpUtils.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpUtils();
                }
            }
        }
        return mInstance;
    }

    /**
     * GET同步请求
     *
     * @param url
     * @return Response
     * @throws IOException
     */
    private Response okGetResultResponse(String url) throws IOException {
        Request mRequest = new Request.Builder().url(url).build();
        Call call = mClient.newCall(mRequest);
        return call.execute();
    }

    /**
     * Get同步请求
     *
     * @param url
     * @return String
     * @throws IOException
     */
    private String okGetResultString(String url) throws IOException {
        Response execute = okGetResultResponse(url);
        ResponseBody body = execute.body();
        if (body != null) {
            return body.string();
        }
        return null;
    }

    /**
     * Get异步请求
     *
     * @param url
     * @param callback
     */

    private void okGetResultCallBack(String url, final OkGetCallback callback) {
        Request request = new Request.Builder().url(url).build();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onError(call, e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                callback.onSuccessful(call, response);
            }
        });
    }

    /**
     * Post同步
     *
     * @param maps
     * @param urlText
     * @return String
     * @throws IOException
     */

    private String okPostString(Map<String, Object> maps, String urlText) throws IOException {

        Request request = postBuild(maps, urlText);
        final Response response = mClient.newCall(request).execute();
        ResponseBody body = response.body();
        if (body != null) {
            String s = body.string();
            body.close();
            return s;
        }
        return null;
    }

    /**
     * Post异步
     *
     * @param maps
     * @param urlText
     * @param callback
     */

    private void okPostCallBack(Map<String, Object> maps, String urlText, final OkPostCallback callback) {
        Request request = postBuild(maps, urlText);
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callback.onError(call, e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                callback.onSuccessful(call, response);
            }
        });
    }


    private Request postBuild(Map<String, Object> maps, String urlText) {
        FormBody.Builder builder = new FormBody.Builder();

        for (Map.Entry<String, Object> entry : maps.entrySet()) {
            String key = entry.getKey();
            String value = (String) entry.getValue();
            builder.add(key, value);
        }
        FormBody formBody = builder
                .build();
        return new Request
                .Builder()
                .post(formBody)
                .url(urlText)
                .build();
    }


    /********************************************************************/
    /**************************暴露的方法********************************/
    /********************************************************************/
    public String GetResult(String url) throws IOException {
        return getInstance().okGetResultString(url);
    }

    public void GetAysnc(String url, OkGetCallback callback) {
        getInstance().okGetResultCallBack(url, callback);
    }

    public String PostResult(Map<String, Object> maps, String urlText) throws IOException {
        return getInstance().okPostString(maps, urlText);
    }

    public void PostAysnc(Map<String, Object> maps, String urlText, OkPostCallback callback) {
        getInstance().okPostCallBack(maps, urlText, callback);
    }
}
