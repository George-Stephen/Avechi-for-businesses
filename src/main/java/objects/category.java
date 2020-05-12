package objects;

import java.util.Objects;

public class category {
    private int id;
    private String name;
    private String description;

    public category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        category category1 = (category) o;
        return Objects.equals(name, category1.name) &&
                Objects.equals(description, category1.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
