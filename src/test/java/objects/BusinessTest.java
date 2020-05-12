package objects;

import dao.Sql2oBusinessDao;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class BusinessTest{

    private static Sql2oBusinessDao sql2oBusinessDao;
    @Before
    public void setUp() throws Exception {
        //uncomment the two lines below to run locally and change to your  credentials
        String connectionString = "jdbc:postgresql://localhost:5432/azure_test";




        sql2o=new Sql2oDepartmentsDao(sql2o);
        sql2oUsersDao=new Sql2oUsersDao(sql2o);
        sql2oNewsDao=new Sql2oNewsDao(sql2o);
        System.out.println("connected to database");
        conn=sql2o.open();

    }

    @After
    public void tearDown() throws Exception {
        sql2oDepartmentsDao.clearAll();
        sql2oUsersDao.clearAll();
        sql2oNewsDao.clearAll();
        System.out.println("clearing database");
    }
    @AfterClass
    public static void shutDown() throws Exception{
        conn.close();
        System.out.println("connection closed");
    }


    @Test
    public void instanciatesBusiness_true(){
        Business testBusiness = new Business("Automix inc","George","yazz@gmail.com","0723053045","automobiles","www.automix.com");
        assertEquals(true,testBusiness instanceof Business);
    }

    @Test
    public void getName() {
        Business testBusiness = new Business("Automix inc","George","yazz@gmail.com","0723053045","automobiles","www.automix.com");
        assertEquals("Automix inc",testBusiness.getName());
    }

    @Test
    public void setName() {
        Business testBusiness = new Business("Automix inc","George","yazz@gmail.com","0723053045","automobiles","www.automix.com");
        testBusiness.setName("Automake inc");
        assertEquals("Automake inc",testBusiness.getName());
    }

    @Test
    public void getOwner() {
        Business testBusiness = new Business("Automix inc","George","yazz@gmail.com","0723053045","automobiles","www.automix.com");
        assertEquals("George",testBusiness.getOwner());
    }

    @Test
    public void setOwner() {
        Business testBusiness = new Business("Automix inc","George","yazz@gmail.com","0723053045","automobiles","www.automix.com");
        testBusiness.setOwner("Asaph");
        assertEquals("Asaph",testBusiness.getOwner());
    }

    @Test
    public void getEmail() {
        Business testBusiness = new Business("Automix inc","George","yazz@gmail.com","0723053045","automobiles","www.automix.com");
        assertEquals("yazz@gmail.com",testBusiness.getEmail());
    }

    @Test
    public void setEmail() {
        Business testBusiness = new Business("Automix inc","George","yazz@gmail.com","0723053045","automobiles","www.automix.com");
        testBusiness.setEmail("auto@gmail.com");
        assertEquals("auto@gmail.com",testBusiness.getEmail());
    }

    @Test
    public void getPhone() {
        Business testBusiness = new Business("Automix inc","George","yazz@gmail.com","0723053045","automobiles","www.automix.com");
        assertEquals("0723053045",testBusiness.getPhone());
    }

    @Test
    public void setPhone() {
        Business testBusiness = new Business("Automix inc","George","yazz@gmail.com","0723053045","automobiles","www.automix.com");
        testBusiness.setPhone("0786720578");
        assertEquals("0786720578",testBusiness.getPhone());
    }

    @Test
    public void getCategory() {
        Business testBusiness = new Business("Automix inc","George","yazz@gmail.com","0723053045","automobiles","www.automix.com");
        assertEquals("automobiles",testBusiness.getCategory());
    }

    @Test
    public void setCategory() {
        Business testBusiness = new Business("Automix inc","George","yazz@gmail.com","0723053045","automobiles","www.automix.com");
        testBusiness.setCategory("cars sale");
        assertEquals("cars sale",testBusiness.getCategory());
    }

    @Test
    public void getWebsite() {
        Business testBusiness = new Business("Automix inc","George","yazz@gmail.com","0723053045","automobiles","www.automix.com");
        assertEquals("www.automix.com",testBusiness.getWebsite());
    }

    @Test
    public void setWebsite() {
        Business testBusiness = new Business("Automix inc","George","yazz@gmail.com","0723053045","automobiles","www.automix.com");
        testBusiness.setWebsite("www.cars.com");
        assertEquals("www.cars.com",testBusiness.getWebsite());
    }

}