package com.example.convertor;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

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
    }

    
}