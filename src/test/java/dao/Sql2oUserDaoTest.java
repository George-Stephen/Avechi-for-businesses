package dao;

import objects.review;
import objects.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import dao.Sql2oReviewDao;
import static org.junit.Assert.*;

public class Sql2oUserDaoTest {

    private static Sql2oReviewDao sql2oReviewDao;
    private static Sql2oUserDao sql2oUserDao;
    private static Connection conn;

    @Before
    public void setUp() throws Exception {


        String connectionString = "";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oReviewDao Sql2oReviewDao;
        sql2oUserDao=new Sql2oUserDao(sql2o);
        System.out.println("connected to database");
        conn=sql2o.open();

    }

    @After
    public void tearDown() throws Exception {
        sql2oReviewDao.clearAll();
        sql2oUserDao.clearAll();
        System.out.println("clearing database");
    }
    @AfterClass
    public static void shutDown() throws Exception{
        conn.close();
        System.out.println("connection closed");
    }


    @Test
    public void addingUserSetsUserId() {
        User user = setUpNewUser();
        int originalId= user.getId();
        sql2oUserDao.add(user);
        assertNotEquals(originalId,user.getId());
    }

    @Test
    public void addedUserIsReturnedCorrectly() {
        User user = setUpNewUser();
        sql2oUserDao.add(user);
        assertEquals(user.getName(),sql2oUserDao.findById(user.getId()).getName());
    }

    @Test
    public void allInstancesAreReturned() {

        User users=setUpNewUser();
        User otherUser= new User("sam","sam@sam.com","345-456-879", "qwerty");
        sql2oUserDao.add(users);
        sql2oUserDao.add(otherUser);
        assertEquals(users.getName(),sql2oUserDao.getAllUsers().get(0).getName());
        assertEquals(otherUser.getName(),sql2oUserDao.getAllUsers().get(1).getName());
    }
    @Test
    public void getReviewsByUser() {
        review review=setUpNewReview();
        review otherReview = new review(1,"sam", "Good work ethic", 6);
        sql2oReviewDao.add(review);
        sql2oReviewDao.add(otherReview);
        User user=setUpNewUser();
        User otherUser= new User("sam","sam@sam.com","345-567-890","qwerty");
        sql2oUserDao.add(user);
        sql2oUserDao.add(otherUser);
        sql2oReviewDao.add(review);
        sql2oReviewDao.add(review);
        sql2oReviewDao.add(review);
        assertEquals(2,sql2oUserDao.getAllReviewsBelongingToUsers(user.getId()).size());
        assertEquals(1,sql2oUserDao.getAllReviewsBelongingToUsers(otherUser.getId()).size());
    }

    //helper
    private User setUpNewUser() {
        return new User("sam","sam@sam.com","123-345-678","qwerty");
    }
    private review setUpNewReview() {
        return new review(1,"sam", "good products", 7);
    }
}