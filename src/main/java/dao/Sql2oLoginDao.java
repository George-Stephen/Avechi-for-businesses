package dao;

import objects.Login;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import java.util.List;

public class Sql2oLoginDao implements LoginDao {
    private final Sql2o sql2o;
    public Sql2oLoginDao(Sql2o sql2o) { this.sql2o = sql2o; }

    @Override
    public void add(Login login) {
        String sql = "INSERT INTO login (name, email) VALUES (:name, :email)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(login)
                    .executeUpdate()
                    .getKey();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }


    @Override
    public List<Login> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM login")
                    .executeAndFetch(Login.class);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE from login";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}