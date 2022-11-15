package Models;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Team {
    private String id;
    private String name;
    private String photo;
    private ArrayList<String> members;
    private Project project;

    public Team() {}

    public Team(String id, String name, String photo, ArrayList<String> members, Project project) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.members = members;
        this.project = project;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public ArrayList<String> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<String> memebers) {
        this.members = memebers;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @NonNull
    @Override
    public String toString() {
        return "Team{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", photo='" + photo + '\'' +
                ", members=" + members +
                ", project=" + project +
                '}';
    }
}
