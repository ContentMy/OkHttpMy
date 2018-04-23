package com.bbbbbbbbb.a0416httpproject.io;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bbbbbbbbb.a0416httpproject.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class IoMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_io_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.io_input, R.id.io_output, R.id.io_reader, R.id.io_writer})
    void ioView(View view) {
        switch (view.getId()) {
            case R.id.io_input:
                startActivity(new Intent(IoMainActivity.this, IoMainActivity.class));
                break;
            case R.id.io_output:
                startActivity(new Intent(IoMainActivity.this, IoMainActivity.class));
                break;
            case R.id.io_reader:
                startActivity(new Intent(IoMainActivity.this, IoMainActivity.class));
                break;
            case R.id.io_writer:
                startActivity(new Intent(IoMainActivity.this, IoMainActivity.class));
                break;
        }
    }
}
