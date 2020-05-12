import org.sql2o.Sql2o;

public class db {
    //use the line below if you want to run locally replacing user with db username and pass with db password
    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/azure", "wangui", "33234159");
}

