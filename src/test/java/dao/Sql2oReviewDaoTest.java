package dao;


import objects.Business;
import objects.review;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import dao.Sql2oBusinessDao;
import static org.junit.Assert.*;

public class Sql2oReviewDaoTest {
    private Connection conn;
    private Sql2oReviewDao reviewDao;
    private Sql2oBusinessDao BusinessDao;


    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        reviewDao = new Sql2oReviewDao(sql2o);
        BusinessDao = new Sql2oBusinessDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingReviewSetsId() throws Exception {
        review testReview = setupReview();
        assertEquals(1, testReview.getId());
    }

    @Test
    public void getAll() throws Exception {
        review review1 = setupReview();
        review review2 = setupReview();
        assertEquals(2, reviewDao.getAll().size());
    }

    @Test
    public void getAllReviewsByBusiness() throws Exception {
        Business testBusiness = setupBusiness();
        Business otherBusiness = setupBusiness(); //add in some extra data to see if it interferes
        review review1 = setupReviewForBusiness(testBusiness);
        review review2 = setupReviewForBusiness(testBusiness);
        review reviewForOtherRestaurant = setupReviewForBusiness(otherBusiness);
        assertEquals(2, reviewDao.getAllReviewsByBusiness(testBusiness.getId()).size());
    }

    @Test
    public void deleteById() throws Exception {
        review testReview = setupReview();
        review otherReview = setupReview();
        assertEquals(2, reviewDao.getAll().size());
        reviewDao.deleteById(testReview.getId());
        assertEquals(1, reviewDao.getAll().size());
    }

    @Test
    public void clearAll() throws Exception {
        review testReview = setupReview();
        review otherReview = setupReview();
        reviewDao.clearAll();
        assertEquals(0, reviewDao.getAll().size());
    }

    @Test
    public void timeStampIsReturnedCorrectly() throws Exception {
        Business testBusiness = setupBusiness();
        BusinessDao.add(testBusiness,1);
        review testReview = new review(1, "Samuel", "reliable products", testBusiness.getId());
        reviewDao.add(testReview);

//        long creationTime = testReview.getCreatedat();
//        long savedTime = reviewDao.getAll().get(0).getCreatedat();
//        String formattedCreationTime = testReview.getFormattedCreatedAt();
//        String formattedSavedTime = reviewDao.getAll().get(0).getFormattedCreatedAt();
//        assertEquals(formattedCreationTime,formattedSavedTime);
//        assertEquals(creationTime, savedTime);
    }

    @Test
    public void reviewsAreReturnedInCorrectOrder() throws Exception {
        Business testBusiness = setupBusiness();
        BusinessDao.add(testBusiness,1);
        review testReview = new review(1, "Captain Kirk","Great work ethic" , testBusiness.getId());
        reviewDao.add(testReview);
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException ex){
            ex.printStackTrace();
        }

        review testSecondReview = new review(2, "Mr Spock", "great products", testBusiness.getId());
        reviewDao.add(testSecondReview);

        //assertEquals("great products",
                //reviewDao.getAllReviewsByBusinessSortedNewestToOldest(testBusiness.getId()).get(0).getReview());
    }

    //helpers

    public review setupReview() {
        review review = new review(1, "Kim", "Great products", 6);
        reviewDao.add(review);
        return review;
    }

    public review setupReviewForBusiness(Business business) {
        review review = new review(1, "Kim", "Good work ethic", business.getId());
        reviewDao.add(review);
        return review;
    }

    public Business setupBusiness() {
        Business business = new Business("Samtech", "Sam", "samtech@samtech.com", "503-402-9874", 1, "www.samtech.com");
        BusinessDao.add(business, 1);
        return business;
    }
}