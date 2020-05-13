package objects;

import java.util.Objects;

public class Login{
    private String name;
    private String password;

    public Login (String name, String password) {
        this.name= name;
        this.password= password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Login)) return false;
        Login login = (Login) o;
        return name.equals(login.name) &&
                password.equals(login.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}