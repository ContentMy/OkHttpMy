package com.bbbbbbbbb.a0416httpproject.ok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bbbbbbbbb.a0416httpproject.R;
import com.bbbbbbbbb.a0416httpproject.ok.okmainactivity.OkFileDownActivity;
import com.bbbbbbbbb.a0416httpproject.ok.okmainactivity.OkFileUpActivity;
import com.bbbbbbbbb.a0416httpproject.ok.okmainactivity.OkGetActivity;
import com.bbbbbbbbb.a0416httpproject.ok.okmainactivity.OkImgLoaderActivity;
import com.bbbbbbbbb.a0416httpproject.ok.okmainactivity.OkPostActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OkMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ok_main_get, R.id.ok_main_post, R.id.ok_main_file_up, R.id.ok_main_file_down, R.id.ok_main_img_down})
    void okMainShow(View view) {
        switch (view.getId()) {
            case R.id.ok_main_get:
                startActivity(new Intent(OkMainActivity.this, OkGetActivity.class));
                break;
            case R.id.ok_main_post:
                startActivity(new Intent(OkMainActivity.this, OkPostActivity.class));
                break;
            case R.id.ok_main_file_up:
                startActivity(new Intent(OkMainActivity.this, OkFileUpActivity.class));
                break;
            case R.id.ok_main_file_down:
                startActivity(new Intent(OkMainActivity.this, OkFileDownActivity.class));
                break;
            case R.id.ok_main_img_down:
                startActivity(new Intent(OkMainActivity.this, OkImgLoaderActivity.class));
                break;

        }
    }
}
