package objects;

import dao.Sql2oBusinessDao;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class BusinessTest{

    private static Sql2oBusinessDao sql2oBusinessDao;
    private static Connection conn;



    @Before
    public void setUp() throws Exception {
        //uncomment the two lines below to run locally and change to your  credentials
        String connectionString = "jdbc:postgresql://localhost:5432/azure_test";
        Sql2o sql2o = new Sql2o(connectionString, "wangui", "33234159");

        sql2oBusinessDao=new Sql2oBusinessDao(sql2o);

        System.out.println("connected to database");
        conn=sql2o.open();

    }

    @After
    public void tearDown() throws Exception {
        sql2oBusinessDao.clearAll();
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

    @Test
    public void setUpIdWorks() {
        Business business=new Business("Ruru Crafts","Ruth Wangui","ruru@crafts.com","0714567895","Jewelry","www" +
                ".rurucrafts.com");
        int originalBusinessId=business.getId();
        sql2oBusinessDao.add(business,1);

        assertNotEquals(originalBusinessId,business.getId());
    }

    @Test
    public void allInstancesAreReturned() {
        Business business=new Business("Ruru Crafts","Ruth Wangui","ruru@crafts.com","0714567895","Jewelry","www" +
                ".rurucrafts.com");
        Business otherbusiness=new Business("Perfect Wedding","Lillian Emukule","perfect@weddings.com","0714567895",
                "Wedding", "www.perfectweddings.com");
        Business thirdbusiness=new Business("Super Beauty","Sylvia Achach","super@beauty.com","0714567895",
                "Beauty", "www.superbeauty.com");

        sql2oBusinessDao.add(business,1);
        sql2oBusinessDao.add(otherbusiness,2);
        sql2oBusinessDao.add(thirdbusiness,3);
        System.out.println(sql2oBusinessDao.getAll().size());

        assertEquals(3,sql2oBusinessDao.getAll().size());
    }
}