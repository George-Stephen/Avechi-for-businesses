package dao;

import objects.Business;
import objects.User;

import java.util.List;

public interface BusinessDao {

    //create

    void add(Business business, User user);

    //read

    List<Business> getAll();
    List<Business> searchByCategory(String category);
    Business findById(int id);
    List<Business> filterByMostRecent();


    //update
    void updateBusinessById(int id);

    //delete

    void deleteById(int business_id,int user_id);
}
