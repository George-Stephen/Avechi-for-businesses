package dao;

import objects.Business;
import objects.User;
import objects.category;
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
            String queryAddBusiness="INSERT INTO business (name,owner,email,phone,category_id,website,creation) " +
                    "VALUES (:name,:owner,:email,:phone,:category_id,:website,:creation) ";
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
    public List<Business> searchByCategory(int category_id) {
        try (Connection con=sql2o.open()){
            String sql="SELECT * FROM business WHERE category_id=:category_id";
            return con.createQuery(sql).addParameter("category_id",category_id).executeAndFetch(Business.class);
        }
    }

    @Override
    public Business findById(int id) {
        try (Connection con=sql2o.open()){
            String sql="SELECT * FROM business WHERE id=:id";
            return con.createQuery(sql).addParameter("id",id).executeAndFetchFirst(Business.class);
        }
    }

    @Override
    public List<Business> filterByMostRecent() {
        try (Connection con=sql2o.open()){
            String sql="SELECT * FROM business ORDER BY creation DESC";
            return con.createQuery(sql)
                    .executeAndFetch(Business.class);
        }
    }

    @Override
    public void updateBusinessById(int id,Business updatedBusiness) {
        try (Connection con=sql2o.open()){
            String sql="UPDATE business SET name=:name,owner=:owner,email=:email,phone=:phone," +
                    "category_id=:category_id,website=:website WHERE id=:id";
            con.createQuery(sql)
                    .addParameter("id",id)
                    .bind(updatedBusiness)
                    .executeUpdate();

        }

    }

    @Override
    public void deleteById(int business_id) {
        try (Connection con=sql2o.open()){
            String sql="DELETE FROM business WHERE id=:id";
            String sql2="DELETE FROM business_users WHERE business_id=:business_id";
            con.createQuery(sql).addParameter("id",business_id).executeUpdate();
            con.createQuery(sql2).addParameter("business_id",business_id).executeUpdate();
        }

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
