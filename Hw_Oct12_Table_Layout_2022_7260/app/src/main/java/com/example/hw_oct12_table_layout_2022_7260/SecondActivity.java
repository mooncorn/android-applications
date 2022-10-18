package com.example.hw_oct12_table_layout_2022_7260;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.hw_oct12_table_layout_2022_7260.Model.MyMenu;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edDescription;
    RadioGroup rgTextColor, rgBackgroundColor;
    RadioButton rbRed, rbGreen, rbMagenta, rbYellow, rbWhite;
    Button btnReturn;

    String description;
    int textColor;
    int backgroundColor;

    MyMenu[] listOfMenus;
    int menuIndex;

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
        // Add click event to btnReturn
        btnReturn.setOnClickListener(view -> save());
        // Add click event to rButtons
        rbRed.setOnClickListener(this);
        rbGreen.setOnClickListener(this);
        rbMagenta.setOnClickListener(this);
        rbYellow.setOnClickListener(this);
        rbWhite.setOnClickListener(this);

        // Initialize listOfMenus
        listOfMenus = (MyMenu[]) getIntent().getSerializableExtra("ListOfMenus");
        menuIndex = getIntent().getIntExtra("MenuIndex", -1);
        description = getIntent().getStringExtra("Description");
        textColor = getIntent().getIntExtra("TextColor", 0);
        backgroundColor = getIntent().getIntExtra("BackgroundColor", 0);

        edDescription.setText(description);

        switch (textColor) {
            case Color.RED:
                rbRed.setChecked(true);
                break;
            case Color.GREEN:
                rbGreen.setChecked(true);
                break;
            case Color.MAGENTA:
                rbMagenta.setChecked(true);
        }

        switch (backgroundColor) {
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
                textColor = Color.RED;
                break;
            case R.id.rbGreen:
                textColor = Color.GREEN;
                break;
            case R.id.rbMagenta:
                textColor = Color.MAGENTA;
                break;
            case R.id.rbWhite:
                backgroundColor = Color.WHITE;
                break;
            case R.id.rbYellow:
                backgroundColor = Color.YELLOW;
                break;
        }
    }

    public void save() {
        Intent i = new Intent();
        String newDescription = edDescription.getText().toString();
        int newTextColor = 0;
        int newBackgroundColor = 0;

        switch (rgTextColor.getCheckedRadioButtonId()) {
            case R.id.rbRed:
                newTextColor = Color.RED;
                break;
            case R.id.rbGreen:
                newTextColor = Color.GREEN;
                break;
            case R.id.rbMagenta:
                newTextColor = Color.MAGENTA;
        }

        switch (rgBackgroundColor.getCheckedRadioButtonId()) {
            case R.id.rbYellow:
                newBackgroundColor = Color.YELLOW;
                break;
            case R.id.rbWhite:
                newBackgroundColor = Color.WHITE;
                break;
        }

        MyMenu menu = listOfMenus[menuIndex];
        menu.setDescription(newDescription);
        menu.setBackgroundColor(newBackgroundColor);
        menu.setTextColor(newTextColor);

        i.putExtra("ListOfMenus", listOfMenus);
        setResult(RESULT_OK, i);
        finish();
    }
}