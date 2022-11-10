package com.example.firebasecrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText edId, edPrice;
    RadioGroup rgBrand;
    RadioButton rbToyota, rbMazda, rbHyundai;
    CheckBox cbStatus;
    Button btnAdd, btnUpdate, btnDelete, btnFind, btnList;

    DatabaseReference carDatabase, carChild;

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
        carDatabase = FirebaseDatabase.getInstance().getReference("Car");
    }

    public void onAddClicked() {
        int id = Integer.parseInt(edId.getText().toString());
        String brand = getBrandString();
        boolean used = cbStatus.isChecked();
        double price = Double.parseDouble(edPrice.getText().toString());

        Car car = new Car(id, brand, used, price);
        System.out.println(car);

        try {
            FirebaseDatabase.getInstance().getReference("car").child(edId.getText().toString()).setValue(car);
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
        String id = edId.getText().toString();
        carChild = carDatabase.child(id);
        carChild.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String id = dataSnapshot.child("id").getValue().toString();
                    String price = dataSnapshot.child("price").getValue().toString();
                    edId.setText(id);
                    edId.setText(price);
                } else {
                    showToast("Does not exist");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void onListClicked() {

    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}