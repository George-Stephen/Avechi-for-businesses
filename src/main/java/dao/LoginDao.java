package dao;

import objects.Login;
import java.util.List;

public interface LoginDao {
    //create
    void add(Login login);

    //read
    List<Login> getAll();

    //delete
    void clearAll();
}
