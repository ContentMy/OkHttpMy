package com.bbbbbbbbb.a0416httpproject.ok.okmainactivity.utils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by ld
 * Created on 2018/4/20
 */

public interface OkPostCallback {
    void onError(Call call, IOException e);

    void onSuccessful(Call call, Response response);
}
