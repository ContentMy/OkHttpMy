package com.bbbbbbbbb.a0416httpproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bbbbbbbbb.a0416httpproject.ok.OkMainActivity;
import com.bbbbbbbbb.a0416httpproject.ok.okmainactivity.utils.SharePersistent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.ok_jump)
    Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        SharedPreferences sharedPreferences = getSharedPreferences("rshansb", Context.MODE_PRIVATE);
//        int sonNum = sharedPreferences.getInt("son", 0);
//        Toast.makeText(this, "" + sonNum, Toast.LENGTH_SHORT).show();
//        sharedPreferences.edit().putInt("son", 1000).apply();
//        int anInt = SharePersistent.getInstance().getInt(this, "hahaha", 0);
//        Toast.makeText(this, "" + anInt, Toast.LENGTH_SHORT).show();
//        SharePersistent.getInstance().put(this, "hahaha", 123);
    }

    @OnClick({R.id.ok_jump, R.id.io_jump, R.id.exit_app})
    void jumpView(View view) {
        switch (view.getId()) {
            case R.id.ok_jump:
                startActivity(new Intent(MainActivity.this, OkMainActivity.class));
                break;
            case R.id.exit_app:
                MainActivity.this.finish();
//                Intent in = new Intent(MainActivity.this, MainActivity.class);
//                MainActivity.this.startActivity(in);
//                overridePendingTransition(0, 0);
                break;
        }
    }

}
