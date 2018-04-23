package com.bbbbbbbbb.a0416httpproject.ok.okmainactivity;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.bbbbbbbbb.a0416httpproject.R;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkFileUpActivity extends AppCompatActivity {
    private String urlPath = "http://192.168.137.1:8080/simple/myapi/file/imageUpload";
    private OkHttpClient mClient;
    public static final MediaType MEDIA_TYPE_FILE = MediaType.parse("application/json; charset=utf-8");
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_file_up);
        verifyStoragePermissions(this);
        mClient = new OkHttpClient();
        File file = new File(Environment.getExternalStorageDirectory(), "123.png");
//        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        if (file.exists()) {
            Log.e("aaabbb", "onCreate: " + file);
            upFile(file);
        }
    }

    private void upFile(File file) {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        RequestBody fileBody = RequestBody.create(MEDIA_TYPE_FILE, file);
        builder.addFormDataPart("image", file.getName(), fileBody);
        builder.addFormDataPart("name", "123.png");

        Request request = new Request.Builder()
                .url(urlPath)
                .post(builder.build())
                .build();

        Call call = mClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("eee", "onFailure: " + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("eee", "成功");
            }
        });
    }
}
