package com.bbbbbbbbb.a0416httpproject.ok.okmainactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bbbbbbbbb.a0416httpproject.R;
import com.bbbbbbbbb.a0416httpproject.ok.okmainactivity.utils.OkGetCallback;
import com.bbbbbbbbb.a0416httpproject.ok.okmainactivity.utils.OkHttpUtils;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkGetActivity extends AppCompatActivity {
    @BindView(R.id.ok_get_one)
    Button button;
    @BindView(R.id.ok_get_two)
    Button two;
    @BindView(R.id.ok_get_text)
    TextView textView;
    private String urlString = "https://www.baidu.com";
    private String urlStringTwo = "https://www.qq.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_get);
        ButterKnife.bind(this);
        //请求开始
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            final String text = OkHttpUtils.getInstance().GetResult(urlString);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText(text);
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("");
                OkHttpUtils.getInstance().GetAysnc(urlString, new OkGetCallback() {
                    @Override
                    public void onError(Call call, final IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(OkGetActivity.this, "" + e.toString(), Toast.LENGTH_SHORT).show();
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
                                        textView.setText(textS);
                                    }
                                });
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        });
    }

    private void sokGetView() {
        //创建OkHttpClient对象
        OkHttpClient mClient = new OkHttpClient();
        //创建一个Request
        Request mRequest = new Request.Builder().url(urlString).build();
        //请求call
        Call mCall = mClient.newCall(mRequest);
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("eee", "onFailure:" + "出错了");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String htmlStr = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(htmlStr);
                    }

                });
            }
        });
    }
}
