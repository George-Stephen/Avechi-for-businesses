package dao;

import objects.review;
import java.util.List;

public interface ReviewDao {

    //create
    void add(review review);

    //read
    List<review> getAll();
    List<review> getAllReviewsByBusiness(int businessId);
    List<review> getAllReviewsByBusinessSortedNewestToOldest(int businessId);

    //update
    //omit for now

    //delete
    void deleteById(int id);
    void clearAll();
}