package dao;

import objects.Business;
import objects.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class Sql2oBusinessDao implements BusinessDao {

    private final Sql2o sql2o;

    public Sql2oBusinessDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Business business, int user_id) {

        try (Connection con=sql2o.open()){
            String queryAddBusiness="INSERT INTO business (name,owner,email,phone,category,website,creation) " +
                    "VALUES (:name,:owner,:email,:phone,:category,:website,:date) ";
            String joinQuery="INSERT INTO business_users (user_id,business_id) VALUES (:user_id,:business_id)";
            int id=(int) con.createQuery(queryAddBusiness,true)
                    .bind(business)
                    .executeUpdate()
                    .getKey();
            business.setId(id);
            con.createQuery(joinQuery).addParameter("user_id",user_id)
                    .addParameter("business_id",business.getId())
                    .executeUpdate();

        }

    }

    @Override
    public List<Business> getAll() {
        try (Connection con=sql2o.open()){
            String sql="SELECT * FROM business";
            return con.createQuery(sql)
                    .executeAndFetch(Business.class);
        }

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

    @Override
    public void clearAll() {

        try (Connection con=sql2o.open()){
            String sql="DELETE FROM business ";
            String sql2="DELETE FROM business_users ";
            con.createQuery(sql).executeUpdate();
            con.createQuery(sql2).executeUpdate();


        }

    }
}
