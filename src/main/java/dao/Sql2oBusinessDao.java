package dao;

import objects.Business;
import objects.User;

import java.util.List;

public class Sql2oBusinessDao implements BusinessDao {
    @Override
    public void add(Business business, User user) {



    }

    @Override
    public List<Business> getAll() {
        return null;
    }

    @Override
    public List<Business> searchByCategory(String category) {
        return null;
    }

    @Override
    public Business findById(int id) {
        return null;
    }

    @Override
    public List<Business> filterByMostRecent() {
        return null;
    }

    @Override
    public void updateBusinessById(int id) {

    }

    @Override
    public void deleteById(int business_id, int user_id) {

    }
}
