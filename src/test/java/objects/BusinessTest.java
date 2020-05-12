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


    @Test
    public void instanciatesBusiness_true(){
        Business testBusiness = new Business("Automix inc","George","yazz@gmail.com","0723053045",1,"www.automix.com");
        assertEquals(true,testBusiness instanceof Business);
    }

    @Test
    public void getName() {
        Business testBusiness = new Business("Automix inc","George","yazz@gmail.com","0723053045",1,"www.automix.com");
        assertEquals("Automix inc",testBusiness.getName());
    }

    @Test
    public void setName() {
        Business testBusiness = new Business("Automix inc","George","yazz@gmail.com","0723053045",1,"www.automix.com");
        testBusiness.setName("Automake inc");
        assertEquals("Automake inc",testBusiness.getName());
    }

    @Test
    public void getOwner() {
        Business testBusiness = new Business("Automix inc","George","yazz@gmail.com","0723053045",1,"www.automix.com");
        assertEquals("George",testBusiness.getOwner());
    }

    @Test
    public void setOwner() {
        Business testBusiness = new Business("Automix inc","George","yazz@gmail.com","0723053045",1,"www.automix.com");
        testBusiness.setOwner("Asaph");
        assertEquals("Asaph",testBusiness.getOwner());
    }

    @Test
    public void getEmail() {
        Business testBusiness = new Business("Automix inc","George","yazz@gmail.com","0723053045",1,"www.automix.com");
        assertEquals("yazz@gmail.com",testBusiness.getEmail());
    }

    @Test
    public void setEmail() {
        Business testBusiness = new Business("Automix inc","George","yazz@gmail.com","0723053045",1,"www.automix.com");
        testBusiness.setEmail("auto@gmail.com");
        assertEquals("auto@gmail.com",testBusiness.getEmail());
    }

    @Test
    public void getPhone() {
        Business testBusiness = new Business("Automix inc","George","yazz@gmail.com","0723053045",1,"www.automix.com");
        assertEquals("0723053045",testBusiness.getPhone());
    }

    @Test
    public void setPhone() {
        Business testBusiness = new Business("Automix inc","George","yazz@gmail.com","0723053045",1,"www.automix.com");
        testBusiness.setPhone("0786720578");
        assertEquals("0786720578",testBusiness.getPhone());
    }

    @Test
    public void getCategory() {
        Business testBusiness = new Business("Automix inc","George","yazz@gmail.com","0723053045",1,"www.automix.com");
        assertEquals(1,testBusiness.getCategory_id());
    }

    @Test
    public void setCategory() {
        Business testBusiness = new Business("Automix inc","George","yazz@gmail.com","0723053045",1,"www.automix.com");
        testBusiness.setCategory_id(2);
        assertEquals(2,testBusiness.getCategory_id());
    }

    @Test
    public void getWebsite() {
        Business testBusiness = new Business("Automix inc","George","yazz@gmail.com","0723053045",1,"www.automix.com");
        assertEquals("www.automix.com",testBusiness.getWebsite());
    }

    @Test
    public void setWebsite() {
        Business testBusiness = new Business("Automix inc","George","yazz@gmail.com","0723053045",1,"www.automix.com");
        testBusiness.setWebsite("www.cars.com");
        assertEquals("www.cars.com",testBusiness.getWebsite());
    }


}