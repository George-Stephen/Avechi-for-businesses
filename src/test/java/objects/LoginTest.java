package objects;


import org.junit.Test;


import static org.junit.Assert.*;

public class LoginTest{
    @Test
    public void instantiatesLogin_true(){
        Login testLogin = new Login("Nyawira", "12345");
        assertEquals(true, testLogin instanceof Login);
    }
    @Test
    public void getName(){
        Login testLogin = new Login("Nyawira", "12345");
        assertEquals("Nyawira", testLogin.getName());
    }
    @Test
    public void setName(){
        Login testLogin = new Login("Nyawira", "12345");
        testLogin.setName("Nyawira");
        assertEquals("Nyawira", testLogin.getName());
    }
    @Test
    public void getPassword(){
        Login testLogin = new Login("Nyawira", "12345");
        assertEquals("12345", testLogin.getPassword());
    }
    @Test
    public void setPassword(){
        Login testLogin = new Login("Nyawira", "12345");
        testLogin.setPassword("12345");
        assertEquals("12345", testLogin.getPassword());
    }
}