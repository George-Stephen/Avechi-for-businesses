package dao;




import objects.Login;
import objects.User;
import objects.review;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oUserDao implements UserDao {
    private final Sql2o sql2o;
    public Sql2oUserDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }


    @Override
    public void add(User user) {
        String sql = "INSERT INTO signin(name, email, phone, password) VALUES (:name, :email, :phone, :password)";
        try(Connection conn = sql2o.open()){
            int id = (int) conn.createQuery(sql, true)
                    .bind(user)
                    .executeUpdate()
                    .getKey();
            user.setId(id);
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM signin";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(sql)
                    .executeAndFetch(User.class);
        }
    }

    @Override
    public User findById(int id) {
        String sql = "SELECT * FROM signin WHERE id = :id";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(User.class);
        }
    }

    @Override
    public void addReviewToUser(review review, User user) {
        String sql = "INSERT INTO signin_review(signin_id, review_id) VALUES (:signin_id, :review_id)";
        try(Connection conn = sql2o.open()){
            conn.createQuery(sql)
                    .addParameter("review_id", review.getId())
                    .addParameter("signin_id", user.getId())
                    .throwOnMappingFailure(false)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<review> getAllReviewsBelongingToUsers(int signin_id) {
        ArrayList<review> allreview = new ArrayList<>();
        String joinQuery = "SELECT review_id FROM signin_review WHERE signin_id = :signin_id";
        try(Connection conn = sql2o.open()){
            List<Integer> allReviewIds = conn.createQuery(joinQuery)
                    .addParameter("signin_id", signin_id)
                    .executeAndFetch(Integer.class);
            for(Integer review_id : allReviewIds){
                String reviewQuery = "SELECT * FROM review WHERE id = :review_id";
                allreview.add(conn.createQuery(reviewQuery)
                        .addParameter("review_id", review_id)
                        .executeAndFetchFirst(review.class));
            }
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
        return allreview;
    }


    @Override
    public void deleteById(int id) {
        String sql = "DELETE from signin WHERE id = :id";
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
        String sql = "DELETE from signin";
        try(Connection conn = sql2o.open()){
            conn.createQuery(sql).executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
    public User validate(String name,String password){
        String sql = "SELECT * FROM signin WHERE name = :name && password = :password";
         try( Connection conn = sql2o.open()) {
             return conn.createQuery(sql)
                     .addParameter("name", name)
                     .addParameter("password", password)
                     .executeAndFetchFirst(User.class);
         }
    }
    public User findOwnerId(String Owner){
        String sql = "SELECT * FROM  signin WHERE name = :owner";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("owner",Owner)
                    .executeAndFetchFirst(User.class);
        }
    }

}