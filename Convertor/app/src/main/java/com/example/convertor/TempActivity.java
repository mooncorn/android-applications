package com.example.convertor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TempActivity extends AppCompatActivity {

    TextView txtName;
    EditText txtTempF, txtTempC;
    Button btnConvert, btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        txtName = findViewById(R.id.txtName);
        txtTempF = findViewById(R.id.txtTempF);
        txtTempC = findViewById(R.id.txtTempC);
        btnConvert = findViewById(R.id.btnConvert);
        btnReturn = findViewById(R.id.btnReturn);

        btnConvert.setOnClickListener(view -> onClickBtnConvert());
        btnReturn.setOnClickListener(view -> onClickBtnReturn());

        Intent receiverIntent = getIntent();
        String name = receiverIntent.getStringExtra(MainActivity.EXTRA_TEXT);
        txtName.setText("App developed by: " + name);
    }

    private void onClickBtnConvert() {
        double tempC = Double.parseDouble(txtTempC.getText().toString());
        txtTempF.setText(ConvertToF(tempC) + "");
    }

    private void onClickBtnReturn() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private double ConvertToF(double tempC) {
        return Math.floor((tempC * 9/2) + 32);
    }
}