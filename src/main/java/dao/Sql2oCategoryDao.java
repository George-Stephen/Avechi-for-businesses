package dao;

import objects.Business;
import objects.category;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class Sql2oCategoryDao implements CategoryDao {

    private final Sql2o sql2o;

    public Sql2oCategoryDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(category newCategory) {
        try(Connection con=sql2o.open()){
            String sql="INSERT INTO categories (name,description) VALUES (:name,:description) ";

            int id=(int) con.createQuery(sql,true)
                    .bind(newCategory)
                    .executeUpdate()
                    .getKey();
            newCategory.setId(id);
        }

    }

    @Override
    public List<category> getAll() {
        try (Connection con=sql2o.open()){
            String sql="SELECT * FROM categories";
            return con.createQuery(sql)
                    .executeAndFetch(category.class);
        }
    }

    @Override
    public category findById(int id) {
        try (Connection con=sql2o.open()){
            String sql="SELECT * FROM categories WHERE id=:id";
            return con.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(category.class);
        }
    }

    @Override
    public void updateCategoryById(int id,category updatedCategory) {
        try (Connection con=sql2o.open()){
            String sql="UPDATE categories SET name=:name,description=:description WHERE id=:id";
            con.createQuery(sql)
                    .addParameter("id",id)
                    .bind(updatedCategory)
                    .executeUpdate();

        }

    }

    @Override
    public void deleteCategoryById(int id) {
        try (Connection con=sql2o.open()){
            String sql="DELETE FROM categories WHERE id=:id";
            con.createQuery(sql).addParameter("id",id).executeUpdate();

        }

    }

    @Override
    public void clearAll() {
        try (Connection con=sql2o.open()){
            String sql="DELETE FROM categories ";
            con.createQuery(sql).executeUpdate();



        }

    }
}
