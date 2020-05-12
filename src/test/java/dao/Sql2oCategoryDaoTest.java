package dao;

import objects.category;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oCategoryDaoTest {

    private static Sql2oCategoryDao sql2oCategoryDao;
    private static Connection conn;



    @Before
    public void setUp() throws Exception {
        //uncomment the two lines below to run locally and change to your  credentials
        String connectionString = "jdbc:postgresql://localhost:5432/azure_test";
        Sql2o sql2o = new Sql2o(connectionString, "wangui", "33234159");

        sql2oCategoryDao=new Sql2oCategoryDao(sql2o);

        System.out.println("connected to database");
        conn=sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        sql2oCategoryDao.clearAll();
        System.out.println("clearing database");
    }
    @AfterClass
    public static void shutDown() throws Exception{
        conn.close();
        System.out.println("connection closed");
    }

    @Test
    public void idIsSetUpCorrectly() {

        category newCategory=new category("Catering","For atering services");
        int oldCategoryId=newCategory.getId();
        sql2oCategoryDao.add(newCategory);
        assertNotEquals(oldCategoryId,newCategory.getId());
    }

    @Test
    public void getAll() {
    }

    @Test
    public void updateCategoryById() {
    }

    @Test
    public void deleteCategoryById() {
    }

    @Test
    public void clearAll() {
    }
}