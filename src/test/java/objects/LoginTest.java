package objects;


import org.junit.Test;


import static org.junit.Assert.*;

public class LoginTest{
    @Test
    public void instantiatesLogin_true(){
        Login testLogin = new Login("Nyawira", "12345");
        assertEquals(true, testLogin instanceof Login);
    }
}