package dao;

import objects.review;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oReviewDao implements ReviewDao {
    private final Sql2o sql2o;
    public Sql2oReviewDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(review review) {
        String sql = "INSERT INTO review (businessid, writtenby, review,rating,createdat) VALUES (:businessId," +
                ":review,:writtenBy,:rating,:createdat)"; //if you change your model, be sure to update here as
        // well!//if you change your model, be sure to update here as well!
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(review)
                    .executeUpdate()
                    .getKey();
            review.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<review> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM review")
                    .executeAndFetch(review.class);
        }
    }

    @Override
    public List<review> getAllReviewsByBusiness(int businessId) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM review WHERE businessid = :businessid")
                    .addParameter("businessid", businessId)
                    .executeAndFetch(review.class);
        }
    }



    @Override
    public void deleteById(int id) {
        String sql = "DELETE from review WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE from review";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }


    @Override
    public List<review> getAllReviewsByBusinessSortedNewestToOldest(int businessId) {
        List<review> unsortedReviews = getAllReviewsByBusiness(businessId); //calling other method!
        List<review> sortedReviews = new ArrayList<>();
        int i = 1;
        for (review review : unsortedReviews){
            int comparisonResult;
            if (i == unsortedReviews.size()) { //we need to do some funky business here to avoid an arrayindex exception and handle the last element properly
                if (review.compareTo(unsortedReviews.get(i-1)) == -1){
                    sortedReviews.add(0, unsortedReviews.get(i-1));
                }
                break;
            }

            else {
                if (review.compareTo(unsortedReviews.get(i)) == -1) { //first object was made earlier than second object
                    sortedReviews.add(0, unsortedReviews.get(i));
                    i++;
                } else if (review.compareTo(unsortedReviews.get(i)) == 0) {//probably should have a tie breaker here as they are the same.
                    sortedReviews.add(0, unsortedReviews.get(i));
                    i++;
                } else {
                    sortedReviews.add(0, unsortedReviews.get(i)); //push the first object to the list as it is newer than the second object.
                    i++;
                }
            }
        }
        return sortedReviews;
    }
}