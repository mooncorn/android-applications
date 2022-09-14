package com.example.simplecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextNumber1;
    EditText editTextNumber2;

    RadioGroup radioGroup;

    Button btnCalculate;
    Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNumber1 = findViewById(R.id.edit_text_number_1);
        editTextNumber2 = findViewById(R.id.edit_text_number_2);

        radioGroup = findViewById(R.id.rd_group_1);

        btnCalculate = findViewById(R.id.btn_calculate);
        btnExit = findViewById(R.id.btn_exit);

        btnCalculate.setOnClickListener(view -> onCalculate());
        btnExit.setOnClickListener(view -> onExit());
    }

    protected void onCalculate() {
        double result = 0;
        double number1;
        double number2;
        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();

        try {
            number1 = Double.parseDouble(editTextNumber1.getText().toString());
            number2 = Double.parseDouble(editTextNumber2.getText().toString());
        } catch (Exception exception) {
            Toast.makeText(this, "You need to enter valid numbers.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (checkedRadioButtonId == -1) {
            Toast.makeText(this, "You need to select an option.", Toast.LENGTH_SHORT).show();
            return;
        }

        switch (checkedRadioButtonId) {
            case R.id.rbtn_add:
                result = number1 + number2;
                break;
            case R.id.rbtn_subtract:
                result = number1 - number2;
                break;
            case R.id.rbtn_divide:
                result = number1 / number2;
                break;
            case R.id.rbtn_multiply:
                result = number1 * number2;
                break;
            default:
                break;
        }

        Toast.makeText(this, "Result: " + result, Toast.LENGTH_SHORT).show();
    }

    protected void onExit() {
        System.exit(0);
    }
}