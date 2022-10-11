package com.example.sendingreceivingobjects;

import static com.example.sendingreceivingobjects.MainActivity.PROVIDER_LIST;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ShowAllActivity extends AppCompatActivity {

    TextView txtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        txtContent = findViewById(R.id.txtContent);

        ArrayList<InternetProvider> internetProviderList = (ArrayList<InternetProvider>) getIntent().getSerializableExtra(PROVIDER_LIST);

        StringBuilder sb = new StringBuilder("");
        for (InternetProvider internetProvider : internetProviderList) {
            sb.append(internetProvider.toString());
            sb.append("======================\n");
        }
        txtContent.setText(sb.toString());
    }


}