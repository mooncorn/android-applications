package com.example.firebasecrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.firebasecrud.model.Car;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText edId, edPrice;
    RadioGroup rgBrand;
    RadioButton rbToyota, rbMazda, rbHyundai;
    CheckBox cbStatus;
    Button btnAdd, btnUpdate, btnDelete, btnFind, btnList;

    FirebaseDatabase firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        edId = findViewById(R.id.edId);
        edPrice = findViewById(R.id.edPrice);
        rgBrand = findViewById(R.id.rgBrand);
        rbToyota = findViewById(R.id.rbToyota);
        rbMazda = findViewById(R.id.rbMazda);
        rbHyundai = findViewById(R.id.rbHyundai);
        cbStatus = findViewById(R.id.cbStatus);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnFind = findViewById(R.id.btnFind);
        btnList = findViewById(R.id.btnList);
        btnAdd.setOnClickListener(view -> onAddClicked());
        btnUpdate.setOnClickListener(view -> onUpdateClicked());
        btnDelete.setOnClickListener(view -> onDeleteClicked());
        btnFind.setOnClickListener(view -> onFindClicked());
        btnList.setOnClickListener(view -> onListClicked());

        FirebaseDatabase.getInstance().getReference("message").setValue("hellow worddd");
    }

    public void onAddClicked() {
        int id = Integer.parseInt(edId.getText().toString());
        String brand = getBrandString();
        boolean used = cbStatus.isChecked();
        double price = Double.parseDouble(edPrice.getText().toString());

        Car car = new Car(id, brand, used, price);
        System.out.println(car);

        try {
            FirebaseDatabase.getInstance("https://android-9ccc8-default-rtdb.firebaseio.com").getReference().setValue(car);
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "Car has been added", Toast.LENGTH_SHORT).show();
    }

    private String getBrandString() {
        switch (rgBrand.getCheckedRadioButtonId()) {
            case R.id.rbToyota:
                return rbToyota.getText().toString();
            case R.id.rbMazda:
                return rbMazda.getText().toString();
            case R.id.rbHyundai:
                return rbHyundai.getText().toString();
            default:
                return "";
        }
    }

    public void onUpdateClicked() {

    }

    public void onDeleteClicked() {

    }

    public void onFindClicked() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("msg");

        myRef.setValue("Hello, World!")
                .addOnSuccessListener(unused -> showToast("ok"))
                .addOnFailureListener(e -> showToast("failed"))
                .addOnCanceledListener(() -> showToast("canceled"));
    }

    public void onListClicked() {

    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}