package dao;

import objects.Business;
import objects.User;
import objects.category;

import java.util.List;

public interface BusinessDao {

    //create

    void add(Business business, int user_id);

    //read

    List<Business> getAll();
    List<Business> searchByCategory(int category_id);
    Business findById(int id);
    List<Business> filterByMostRecent();


    //update
    void updateBusinessById(int id,Business updatedBusiness);

    //delete

    void deleteById(int business_id,int user_id);
    void clearAll();
}
