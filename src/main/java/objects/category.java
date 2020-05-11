package objects;

import java.util.Objects;

public class category {
    private int id;
    private String category;
    private String description;

    public category(String category, String description) {
        this.category = category;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        category category1 = (category) o;
        return Objects.equals(category, category1.category) &&
                Objects.equals(description, category1.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, description);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
