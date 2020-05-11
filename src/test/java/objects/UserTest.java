package objects;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void InstantiatesNewUser(){
        User testUser = setupNewuser();
        assertEquals(true, testUser instanceof User);
    }
    @Test
    public void User_get_name(){
        User testUser = setupNewuser();
        assertEquals("Stevie254",testUser.getName());
    }
    @Test
    public void User_get_Email(){
        User testUser = setupNewuser();
        assertEquals("yazz777stevie@gmail.com",testUser.getEmail());
    }
    @Test
    public void User_get_phone(){
        User testUser = setupNewuser();
        assertEquals("0723053045",testUser.getPhone());
    }
    @Test
    public void User_get_password(){
        User testUser = setupNewuser();
        assertEquals("amblessed",testUser.getPassword());
    }

    // helper method
    public User setupNewuser(){
        return new User("Stevie254","yazz777stevie@gmail.com","0723053045","amblessed");
    }

    @Test
     public void User_set_name() {
        User testUser = setupNewuser();
        testUser.setName("Ruth2020");
        assertEquals("Ruth2020",testUser.getName());
    }
    @Test
    public void User_setPassword(){
        User testUser = setupNewuser();
        testUser.setPassword("Business2020");
        assertEquals("Business2020",testUser.getPassword());
    }

}