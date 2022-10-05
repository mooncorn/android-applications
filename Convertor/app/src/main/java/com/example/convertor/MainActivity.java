package com.example.convertor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_TEXT = "com.example.convertor.EXTRA_TEXT";

    Button btnTemp, btnMetric, btnQuit;
    EditText txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTemp = findViewById(R.id.btnTemp);
        btnMetric = findViewById(R.id.btnMetric);
        btnQuit = findViewById(R.id.btnQuit);

        txtName = findViewById(R.id.txtName);

        btnTemp.setOnClickListener(view -> onClickBtnTemp());
        btnMetric.setOnClickListener(view -> onClickBtnMetric());
        btnQuit.setOnClickListener(view -> onClickBtnQuit());
    }

    private void onClickBtnTemp() {
        Intent intent = new Intent(this, TempActivity.class);
        intent.putExtra(EXTRA_TEXT, txtName.getText().toString());
        startActivity(intent);
    }

    private void onClickBtnMetric() {
        Intent intent = new Intent(this, MetricActivity.class);
        intent.putExtra(EXTRA_TEXT, txtName.getText().toString());
        startActivity(intent);
    }

    private void onClickBtnQuit() {
        System.exit(0);
    }
}