import org.sql2o.*;

public class db{
    public static  final String ConnectionString = "jdbc:postgresql://ec2-52-202-146-43.compute-1.amazonaws.com:5432/d9deq3fqtguvhk";
    public static  Sql2o sql2o = new Sql2o(ConnectionString,"cjuayemhpqshgg","95f52320f1a21ee9597761427cacffb4523bad0358f84d4b89e3bcd2016d996b");

}
