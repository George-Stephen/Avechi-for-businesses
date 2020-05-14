import org.sql2o.*;

public class db{
    public static  final String ConnectionString = "jdbc:postgresql://localhost:5432/azure";
    public static  Sql2o sql2o = new Sql2o(ConnectionString,"stephen","stephen2020");


}
