package dao;


import objects.User;
import objects.review;

import java.util.List;

public interface UserDao{
    //create
    void add(User user);

    //Get and find all users ...by id
    List<User>getAllUsers();
    User findById(int id);

    //Add and get reviews by users
    void addReviewToUser(review review, User user);
    List<review> getAllReviewsBelongingToUsers(int signin_id);

    //delete
    void deleteById(int id);

    //clear
    void clearAll();

}