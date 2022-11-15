package Models;

import androidx.annotation.NonNull;

public class Project {
    private String description;
    private String title;
    private int total;

    public Project() {}

    public Project(String description, String title, int total) {
        this.description = description;
        this.title = title;
        this.total = total;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @NonNull
    @Override
    public String toString() {
        return "Project{" +
                "description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", total=" + total +
                '}';
    }
}
