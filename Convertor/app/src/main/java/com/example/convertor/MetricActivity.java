package com.example.convertor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MetricActivity extends AppCompatActivity {

    TextView txtName;
    EditText txtMeters, txtCentimeters, txtKilometers;
    Button btnConvert, btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metric);

        txtName = findViewById(R.id.txtName);
        txtMeters = findViewById(R.id.txtMeters);
        txtCentimeters = findViewById(R.id.txtCentimeters);
        txtKilometers = findViewById(R.id.txtKilometers);
        btnConvert = findViewById(R.id.btnConvert);
        btnReturn = findViewById(R.id.btnReturn);

        btnConvert.setOnClickListener(view -> onClickBtnConvert());
        btnReturn.setOnClickListener(view -> onClickBtnReturn());

        Intent receiverIntent = getIntent();
        String name = receiverIntent.getStringExtra(MainActivity.EXTRA_TEXT);
        txtName.setText(name);
    }

    private void onClickBtnConvert() {
        double meters = Double.parseDouble(txtMeters.getText().toString());
        double centimeters = meters * 100;
        double kilometers = meters / 1000;

        txtCentimeters.setText(centimeters + "");
        txtKilometers.setText(kilometers + "");
    }

    private void onClickBtnReturn() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}