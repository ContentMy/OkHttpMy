package com.bbbbbbbbb.a0416httpproject.ok.okmainactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bbbbbbbbb.a0416httpproject.R;
import com.bbbbbbbbb.a0416httpproject.ok.okmainactivity.utils.OkHttpUtils;
import com.bbbbbbbbb.a0416httpproject.ok.okmainactivity.utils.OkPostCallback;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkPostActivity extends AppCompatActivity {
    @BindView(R.id.post_text_password)
    EditText pwd;
    @BindView(R.id.post_text_username)
    EditText name;
    @BindView(R.id.post_text_result)
    TextView result;
    String passwordString;
    String usernameString;
    long idLong = 101;
    //http://127.0.0.1:8080/simple/myapi/data/addUserByJson
    String urlText = "http://192.168.137.1:8080/simple/myapi/data/addUser";
    private Map<String, Object> maps = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_post);
        ButterKnife.bind(this);
        passwordString = String.valueOf(pwd.getText());
        usernameString = String.valueOf(name.getText());
        maps.put("userId", String.valueOf(idLong));
        maps.put("name", usernameString);
        maps.put("userToken", passwordString);
    }

    @OnClick({R.id.post_button, R.id.post_two, R.id.post_three})
    void buttonClick(View view) {
        switch (view.getId()) {
            case R.id.post_button:
                result.setText("");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            final String s = OkHttpUtils.getInstance().PostForString(maps, urlText);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    result.setText(s);
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
            case R.id.post_two:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            final Response response = OkHttpUtils.getInstance().PostForResponse(maps, urlText);
                            ResponseBody body = response.body();
                            if (body != null) {
                                final String s = body.string();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        result.setText(s);
                                    }
                                });
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
            case R.id.post_three:
                result.setText("");
                OkHttpUtils.getInstance().PostAysnc(maps, urlText, new OkPostCallback() {
                    @Override
                    public void onError(Call call, final IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(OkPostActivity.this, "" + e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onSuccessful(Call call, Response response) {
                        ResponseBody body = response.body();
                        if (body != null) {
                            try {
                                final String textS = body.string();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        result.setText(textS);
                                    }
                                });
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                break;
        }
    }

    private void postView() {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody formBody = new FormBody
                .Builder()
                .add("userId", String.valueOf(idLong))
                .add("name", usernameString)
                .add("userToken", passwordString)
                .build();
        Request request = new Request
                .Builder()
                .post(formBody)
                .url(urlText)
                .build();
        try {
            final Response response = okHttpClient.newCall(request).execute();
            final String stringNmae = response.body().string();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    result.setText(stringNmae);
                }
            });
            response.body().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
