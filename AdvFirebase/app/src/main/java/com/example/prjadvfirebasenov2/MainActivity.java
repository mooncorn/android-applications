package com.example.prjadvfirebasenov2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.UUID;

import model.Car;
import model.Person;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnSuccessListener, OnFailureListener, OnCompleteListener, ValueEventListener {

    EditText edId, edPhoto;
    ImageView imPhoto;
    Button btnAdd, btnBrowse, btnFind, btnUpload;

    DatabaseReference personDatabase;

    //To upload the image file into Firebase database
    FirebaseStorage storage;
    StorageReference storageReference,sRef;

    ActivityResultLauncher actRes;
    Uri filepath;

    ProgressDialog prDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        edId = findViewById(R.id.edId);
        imPhoto = findViewById(R.id.imPhoto);
        btnAdd = findViewById(R.id.btnAdd);
        btnBrowse = findViewById(R.id.btnBrowse);
        btnUpload = findViewById(R.id.btnUpload);
        btnFind = findViewById(R.id.btnFind);

        btnAdd.setOnClickListener(this);
        btnBrowse.setOnClickListener(this);
        btnUpload.setOnClickListener(this);
        btnFind.setOnClickListener(this);

        personDatabase = FirebaseDatabase
                .getInstance()
                .getReference("person");

        // Initialize the Firebase Storage
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        actRes = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                //Display the selected picture from the device
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    filepath = result.getData().getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
                        imPhoto.setImageBitmap(bitmap);
                    } catch (Exception ex) {
                        Toast.makeText(MainActivity.this, ex.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAdd:addPerson();break;
            case R.id.btnFind:findPerson();break;
            case R.id.btnBrowse:selectPhoto();break;
            case R.id.btnUpload:uploadPhoto();break;
        }
    }

    private void addPerson() {
        ArrayList<String> hobbies = new ArrayList<String>();
        hobbies.add("Soccer");
        hobbies.add("Handball");
        hobbies.add("Music");
        Car car = new Car("M400","Mazda","Mazda 6");
        Person person = new Person(400,"Simon the Chipmunk","im1.png",car,hobbies);
        personDatabase.child("400").setValue(person);
        Toast.makeText(this,"A new person has been added successfully", Toast.LENGTH_LONG).show();
    }

    private void findPerson() {
    }

    private void selectPhoto() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        actRes.launch(Intent.createChooser(i,"Please select a photo"));
    }

    private void uploadPhoto() {
        if (filepath != null) {
            prDialog = new ProgressDialog(this);
            prDialog.setTitle("Uploading photo in progress .... ");
            prDialog.show();
            // To store the images inside firebase Storage
            sRef = storageReference.child("images/" + UUID.randomUUID());
            // Upload the picture and see if it fails or succeeds
            sRef.putFile(filepath).addOnSuccessListener(this);
            sRef.putFile(filepath).addOnFailureListener(this);
        }
    }

    @Override
    public void onSuccess(Object o) {
        prDialog.dismiss();
        Toast.makeText(this,"The photo has been uploaded successfully!",Toast.LENGTH_LONG).show();
        sRef.getDownloadUrl().addOnCompleteListener(this);
    }

    @Override
    public void onFailure(@NonNull Exception e) {
        prDialog.dismiss();
        Toast.makeText(this, e.getMessage(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onComplete(@NonNull Task task) {
        String url = task.getResult().toString();
        Person p = new Person();
        p.setPhoto(url);
        personDatabase.child("300").setValue(p);
        Toast.makeText(this,"The photo has been updated!",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        if (snapshot.exists()) {
            Person p = snapshot.getValue(Person.class);

            String url = snapshot.child("photo").getValue().toString();

            Picasso.with(this).load(url).placeholder(R.drawable.temp_image).into(imPhoto);
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}