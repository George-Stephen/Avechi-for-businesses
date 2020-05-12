package dao;

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
            String sql="INSERT INTO category (name,description) VALUES (:name,:description) ";

            int id=(int) con.createQuery(sql,true)
                    .bind(newCategory)
                    .executeUpdate()
                    .getKey();
            newCategory.setId(id);
        }

    }

    @Override
    public List<category> getAll() {
        return null;
    }

    @Override
    public void updateCategoryById(int id) {

    }

    @Override
    public void deleteCategoryById(int id) {

    }
}
