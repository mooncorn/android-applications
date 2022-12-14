package com.example.sendingreceivingobjects;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public static final String PROVIDER_LIST = "providers";

    EditText edtClientNumber, edtNbOfMonths, edtSubtotal, edtTPS, edtTVQ, edtTotal;
    RadioGroup rdgProvider;
    Button btnSave, btnShowAll, btnExit;

    private ArrayList<InternetProvider> internetProviderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();

        edtNbOfMonths.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                onValueChange();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        rdgProvider.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                onValueChange();
            }
        });
    }

    private void initialize() {
        internetProviderList = new ArrayList<>();

        edtClientNumber = findViewById(R.id.editTextClientNumber);
        edtNbOfMonths = findViewById(R.id.editTextNumberOfMonths);
        edtSubtotal = findViewById(R.id.editTextSubtotal);
        edtTPS = findViewById(R.id.editTextTPS);
        edtTVQ = findViewById(R.id.editTextTVQ);
        edtTotal = findViewById(R.id.editTextTotal);
        rdgProvider = findViewById(R.id.rdgProvider);
        btnSave = findViewById(R.id.btnSave);
        btnShowAll = findViewById(R.id.btnShowAll);
        btnExit = findViewById(R.id.btnExit);

        btnSave.setOnClickListener(view -> onClickSave());
        btnShowAll.setOnClickListener(view -> onClickShowAll());
        btnExit.setOnClickListener(view -> onClickExit());
    }

    private void onValueChange() {
        int months;
        Double subtotal = null;

        try {
            months = Integer.parseInt(edtNbOfMonths.getText().toString());
        } catch (Exception e) {
            return;
        }

        if (months < 1 || months > 12) return;

        switch (rdgProvider.getCheckedRadioButtonId()) {
            case R.id.rdbBell:
                subtotal = getSubtotalForBell(months);
                break;

            case R.id.rdbVideotron:
                subtotal = getSubtotalForVideotron(months);
                break;

            case R.id.rdbAcanac:
                subtotal = getSubtotalForAcanac(months);
                break;

            default:
                break;
        }

        if (subtotal == null) return;

        double tps = calculateTPS(subtotal);
        double tvq = calculateTVQ(subtotal);
        double total = subtotal + tps + tvq;

        edtSubtotal.setText(subtotal + "");
        edtTPS.setText(tps + "");
        edtTVQ.setText(tvq + "");
        edtTotal.setText(total + "");
    }

    private void onClickSave() {
        internetProviderList.add(createInternetProvider(true));
        Toast.makeText(this, "Provider saved", Toast.LENGTH_SHORT).show();
    }

    private InternetProvider createInternetProvider(boolean showToastMessages) {

        // Validate Client Number
        String clientNumber = edtClientNumber.getText().toString();

        if (clientNumber.isEmpty()) {
            if (showToastMessages) {
                Toast.makeText(this, "Client number is required", Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        // Validate Providers
        Provider provider;

        switch (rdgProvider.getCheckedRadioButtonId()) {
            case R.id.rdbBell:
                provider = Provider.BELL;
                break;
            case R.id.rdbVideotron:
                provider = Provider.VIDEOTRON;
                break;
            case R.id.rdbAcanac:
                provider = Provider.ACANAC;
                break;
            default:
                provider = null;
                break;
        }

        if (provider == null) {
            if (showToastMessages) {
                Toast.makeText(this, "Provider is required", Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        // Validate Number of Months
        Integer nbOfMonths = null;

        try {
            nbOfMonths = Integer.parseInt(edtNbOfMonths.getText().toString());
        } catch (Exception e) {
            if (showToastMessages)
                Toast.makeText(this, "Number of months is required", Toast.LENGTH_SHORT).show();
        }

        if (nbOfMonths == null || nbOfMonths < 1 || nbOfMonths > 12) {
            if (showToastMessages)
                Toast.makeText(this, "Number of months needs to be  ", Toast.LENGTH_SHORT).show();
            return null;
        }

        return new InternetProvider(clientNumber, provider, nbOfMonths);
    }

    private void onClickShowAll() {
        Intent intent = new Intent(this, ShowAllActivity.class);
        intent.putExtra(PROVIDER_LIST, internetProviderList);
        startActivity(intent);
    }

    private Double getSubtotalForBell(int nbOfMonths) {
        if (nbOfMonths < 1 || nbOfMonths > 12) return null;
        if (nbOfMonths == 12) return 600d;
        return 60d * nbOfMonths;
    }

    private Double getSubtotalForVideotron(int nbOfMonths) {
        if (nbOfMonths < 1 || nbOfMonths > 12) return null;
        if (nbOfMonths == 12) return 70d * 12 * 0.7;
        if (nbOfMonths == 6) return 350d;
        return 70d * nbOfMonths;
    }

    private Double getSubtotalForAcanac(int nbOfMonths) {
        if (nbOfMonths < 1 || nbOfMonths > 12) return null;
        if (nbOfMonths == 12) return 45d * 11;
        return 45d * nbOfMonths;
    }

    private double calculateTPS(double amount) {
        return 0.06 * amount;
    }

    private double calculateTVQ(double amount) {
        return (amount * 3.6) * 0.095;
    }

    private void onClickExit() {
        System.exit(0);
    }
}