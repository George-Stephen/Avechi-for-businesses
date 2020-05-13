package dao;

import objects.Business;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static org.junit.Assert.*;

public class Sql2oBusinessDaoTest {

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
    public void setUpIdWorks() {
        Business business=new Business("Ruru Crafts","Ruth Wangui","ruru@crafts.com","0714567895",1,"www" +
                ".rurucrafts.com");
        int originalBusinessId=business.getId();
        sql2oBusinessDao.add(business,1);
        assertNotEquals(originalBusinessId,business.getId());

    }

    @Test
    public void allInstancesAreReturned() {
        Business business=new Business("Ruru Crafts","Ruth Wangui","ruru@crafts.com","0714567895",1,"www" +
                ".rurucrafts.com");
        Business otherbusiness=new Business("Perfect Wedding","Lillian Emukule","perfect@weddings.com","0714567895",
                2, "www.perfectweddings.com");
        Business thirdbusiness=new Business("Super Beauty","Sylvia Achach","super@beauty.com","0714567895",
                3, "www.superbeauty.com");

        sql2oBusinessDao.add(business,1);
        sql2oBusinessDao.add(otherbusiness,2);
        sql2oBusinessDao.add(thirdbusiness,3);


        assertEquals(3,sql2oBusinessDao.getAll().size());
    }

    @Test
    public void allInstancesOfSelectedCategoryAreReturned() {
        Business business=new Business("Ruru Crafts","Ruth Wangui","ruru@crafts.com","0714567895",1,"www" +
                ".rurucrafts.com");
        Business otherbusiness=new Business("Perfect Wedding","Lillian Emukule","perfect@weddings.com","0714567895",
                1, "www.perfectweddings.com");
        Business thirdbusiness=new Business("Super Beauty","Sylvia Achach","super@beauty.com","0714567895",
                3, "www.superbeauty.com");
        sql2oBusinessDao.add(business,1);
        sql2oBusinessDao.add(otherbusiness,2);
        sql2oBusinessDao.add(thirdbusiness,3);
        assertEquals(2,sql2oBusinessDao.searchByCategory(1).size());

    }

    @Test
    public void findByIdReturnsCorrectInstance() {
        Business business=new Business("Ruru Crafts","Ruth Wangui","ruru@crafts.com","0714567895",1,"www" +
                ".rurucrafts.com");
        Business otherbusiness=new Business("Perfect Wedding","Lillian Emukule","perfect@weddings.com","0714567895",
                1, "www.perfectweddings.com");
        Business thirdbusiness=new Business("Super Beauty","Sylvia Achach","super@beauty.com","0714567895",
                3, "www.superbeauty.com");
        sql2oBusinessDao.add(business,1);
        sql2oBusinessDao.add(otherbusiness,2);
        sql2oBusinessDao.add(thirdbusiness,3);
        assertEquals(thirdbusiness,sql2oBusinessDao.findById(thirdbusiness.getId()));
        assertEquals(business,sql2oBusinessDao.findById(business.getId()));

    }

    @Test
    public void filterByMostRecent() {
        Business business=new Business("Ruru Crafts","Ruth Wangui","ruru@crafts.com","0714567895",1,"www" +
                ".rurucrafts.com");
        sql2oBusinessDao.add(business,1);
        Business otherbusiness=new Business("Perfect Wedding","Lillian Emukule","perfect@weddings.com","0714567895",
                1, "www.perfectweddings.com");
        sql2oBusinessDao.add(otherbusiness,2);
        Business thirdbusiness=new Business("Super Beauty","Sylvia Achach","super@beauty.com","0714567895",
                3, "www.superbeauty.com");


        sql2oBusinessDao.add(thirdbusiness,3);

        assertEquals(thirdbusiness,sql2oBusinessDao.filterByMostRecent().get(0));
    }

    @Test
    public void updateBusinessById() {
        Business business=new Business("Ruru Crafts","Ruth Wangui","ruru@crafts.com","0714567895",1,"www" +
                ".rurucrafts.com");
        String oldBusinessName=business.getName();
        sql2oBusinessDao.add(business,1);
        Business updatedBusiness=new Business("Fabulous Jewelery","Ruth Wangui","ruru@crafts.com","0714567895",1,"www" +
                ".rurucrafts.com");

        sql2oBusinessDao.updateBusinessById(business.getId(),updatedBusiness);
        System.out.println(oldBusinessName);
        System.out.println(sql2oBusinessDao.findById(business.getId()).getName());
        assertNotEquals(oldBusinessName,sql2oBusinessDao.findById(business.getId()).getName());

    }

    @Test
    public void deleteById() {
        Business business=new Business("Ruru Crafts","Ruth Wangui","ruru@crafts.com","0714567895",1,"www" +
                ".rurucrafts.com");
        sql2oBusinessDao.add(business,1);
        Business otherbusiness=new Business("Perfect Wedding","Lillian Emukule","perfect@weddings.com","0714567895",
                1, "www.perfectweddings.com");
        sql2oBusinessDao.add(otherbusiness,2);
        Business thirdbusiness=new Business("Super Beauty","Sylvia Achach","super@beauty.com","0714567895",
                3, "www.superbeauty.com");
        sql2oBusinessDao.add(thirdbusiness,3);

        sql2oBusinessDao.deleteById(business.getId());
        assertEquals(2,sql2oBusinessDao.getAll().size());
        assertEquals(null,sql2oBusinessDao.findById(business.getId()));

    }


}