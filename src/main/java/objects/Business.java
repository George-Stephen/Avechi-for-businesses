package objects;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class Business {
    private  int id;
    private String name;
    private String owner;
    private String email;
    private String phone ;
    private int category_id;
    private String website;
    private Timestamp creation;
    private Date date= new Date();

    public Business(String name, String owner, String email, String phone, int category_id, String website) {
        this.name = name;
        this.owner = owner;
        this.email = email;
        this.phone = phone;
        this.category_id = category_id;
        this.website = website;
        this.creation=new Timestamp(date.getTime());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Business business = (Business) o;
        return Objects.equals(name, business.name) &&
                Objects.equals(owner, business.owner) &&
                Objects.equals(email, business.email) &&
                Objects.equals(phone, business.phone) &&
                Objects.equals(category_id, business.category_id) &&
                Objects.equals(website, business.website) &&
                Objects.equals(creation, business.creation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, owner, email, phone, category_id, website, creation);
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Timestamp getCreation() {
        return creation;
    }

    public void setCreation(Timestamp creation) {
        this.creation = creation;
    }
}
