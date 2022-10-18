package com.example.hw_oct12_table_layout_2022_7260;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.hw_oct12_table_layout_2022_7260.Model.MyMenu;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edDescription;
    RadioGroup rgTextColor, rgBackgroundColor;
    RadioButton rbRed, rbGreen, rbMagenta, rbYellow, rbWhite;
    Button btnReturn;

    MyMenu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initialize();
    }

    private void initialize() {
        edDescription = findViewById(R.id.edDescription);
        rgTextColor = findViewById(R.id.rgTxtColor);
        rgBackgroundColor = findViewById(R.id.rgBackgroundColor);
        rbRed = findViewById(R.id.rbRed);
        rbGreen = findViewById(R.id.rbGreen);
        rbMagenta = findViewById(R.id.rbMagenta);
        rbYellow = findViewById(R.id.rbYellow);
        rbWhite = findViewById(R.id.rbWhite);
        btnReturn = findViewById(R.id.btnReturn);

        btnReturn.setOnClickListener(view -> save());
        rbRed.setOnClickListener(this);
        rbGreen.setOnClickListener(this);
        rbMagenta.setOnClickListener(this);
        rbYellow.setOnClickListener(this);
        rbWhite.setOnClickListener(this);

        menu = (MyMenu) getIntent().getSerializableExtra("Menu");

        edDescription.setText(menu.getDescription());

        switch (menu.getTextColor()) {
            case Color.RED:
                rbRed.setChecked(true);
                break;
            case Color.GREEN:
                rbGreen.setChecked(true);
                break;
            case Color.MAGENTA:
                rbMagenta.setChecked(true);
        }

        switch (menu.getBackgroundColor()) {
            case Color.YELLOW:
                rbYellow.setChecked(true);
                break;
            case Color.WHITE:
                rbWhite.setChecked(true);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.rbRed:
                menu.setTextColor(Color.RED);
                break;
            case R.id.rbGreen:
                menu.setTextColor(Color.GREEN);
                break;
            case R.id.rbMagenta:
                menu.setTextColor(Color.MAGENTA);
                break;
            case R.id.rbWhite:
                menu.setBackgroundColor(Color.WHITE);
                break;
            case R.id.rbYellow:
                menu.setBackgroundColor(Color.YELLOW);
                break;
        }
    }

    public void save() {
        menu.setDescription(edDescription.getText().toString());

        Intent i = new Intent();

        i.putExtra("Menu", menu);
        setResult(RESULT_OK, i);
        finish();
    }
}