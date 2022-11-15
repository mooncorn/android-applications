package com.example.teammanagement;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

import Models.Project;
import Models.Team;

public class MainActivity extends AppCompatActivity {

    EditText edTeamId, edTeamName, edProjectTitle, edProjectDescription, edMember1, edMember2, edMember3, edMember4,  edUserStories;
    TextView tvMembersContent;

    ImageView ivPhoto;
    Uri photoUri;

    Button btnAdd, btnFind, btnList, btnQuit;

    ActivityResultLauncher<Intent> imagePickerActivityResultLauncher;

    DatabaseReference teamsDb;
    StorageReference storage;

    ValueEventListener findEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        edTeamId = findViewById(R.id.edTeamID);
        edTeamName = findViewById(R.id.edTeamName);
        edProjectTitle = findViewById(R.id.edProjectTitle);
        edProjectDescription = findViewById(R.id.edDescription);
        edUserStories = findViewById(R.id.edUserStories);

        tvMembersContent = findViewById(R.id.tv1);
        edMember1 = findViewById(R.id.edMember1);
        edMember2 = findViewById(R.id.edMember2);
        edMember3 = findViewById(R.id.edMember3);
        edMember4 = findViewById(R.id.edMember4);

        ivPhoto = findViewById(R.id.ivPhoto);
        ivPhoto.setOnClickListener(view -> browseForImage());

        btnAdd = findViewById(R.id.btnAdd);
        btnFind = findViewById(R.id.btnFind);
        btnList = findViewById(R.id.btnList);
        btnQuit = findViewById(R.id.btnQuit);

        btnAdd.setOnClickListener(view -> createTeam());
        btnFind.setOnClickListener(view -> findTeam());
        btnList.setOnClickListener(view -> listAllTeams());
        btnQuit.setOnClickListener(view -> quit());

        imagePickerActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), this::receivePickedImage);

        teamsDb = FirebaseDatabase.getInstance().getReference("teams");
        storage = FirebaseStorage.getInstance().getReference();

        findEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mapTeamDataToView(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
    }

    private void receivePickedImage(ActivityResult result) {
        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
            photoUri = result.getData().getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), photoUri);
                ivPhoto.setImageBitmap(bitmap);
            } catch (Exception ex) {
                Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Open photo picker
    private void browseForImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        imagePickerActivityResultLauncher.launch(Intent.createChooser(intent, "Select a photo"));
    }

    // Create a new team and insert it into the database
    private void createTeam() {
        if (photoUri == null) return;

        // Upload image
        String randomUUID = UUID.randomUUID().toString();
        storage.child("images/" + randomUUID).putFile(photoUri);
        String photoUrl = "https://firebasestorage.googleapis.com/v0/b/android-9ccc8.appspot.com/o/images%" + randomUUID + "?alt=media";

        // Create members array
        String member1 = edMember1.getText().toString();
        String member2 = edMember2.getText().toString();
        String member3 = edMember3.getText().toString();
        String member4 = edMember4.getText().toString();

        ArrayList<String> members = new ArrayList<>();
        members.add(member1);
        members.add(member2);
        members.add(member3);
        members.add(member4);

        // Create project and team
        String teamId = edTeamId.getText().toString();
        String teamName = edTeamName.getText().toString();
        String projectTitle = edProjectTitle.getText().toString();
        String projectDescription = edProjectDescription.getText().toString();
        int userStories = Integer.parseInt(edUserStories.getText().toString());

        Project project = new Project(projectDescription, projectTitle, userStories);
        Team team = new Team(teamId, teamName, photoUrl, members, project);

        // Insert team into the database
        teamsDb.child(teamId).setValue(team);

        Toast.makeText(this, "Team created", Toast.LENGTH_SHORT).show();
    }

    // Find team based on Team ID provided
    private void findTeam() {
        teamsDb.removeEventListener(findEventListener);
        teamsDb.addValueEventListener(findEventListener);
    }

    private void mapTeamDataToView(DataSnapshot snapshot) {
        if (snapshot.exists()) {
            String teamId = edTeamId.getText().toString();
            Team team = snapshot.child(teamId).getValue(Team.class);
            System.out.println(team);

            if (team == null) {
                Toast.makeText(MainActivity.this, "Team not found", Toast.LENGTH_SHORT).show();
                return;
            }

            edTeamId.setText(team.getId());
            edTeamName.setText(team.getName());
            setPicture(team.getPhoto());
            edProjectTitle.setText(team.getProject().getTitle());
            edProjectDescription.setText(team.getProject().getDescription());
            edUserStories.setText(String.valueOf(team.getProject().getTotal()));
            edMember1.setText(team.getMembers().get(0));
            edMember2.setText(team.getMembers().get(1));
            edMember3.setText(team.getMembers().get(2));
            edMember4.setText(team.getMembers().get(3));
        }
    }

    // List all teams and their details in the log panel
    private void listAllTeams() {

    }

    // Close the application
    private void quit() {
        System.exit(0);
    }

    private void setPicture(String url) {
        Picasso.with(this).load(url).placeholder(R.drawable.temp_image).into(ivPhoto);
    }

}