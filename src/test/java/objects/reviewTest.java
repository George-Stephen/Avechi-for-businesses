package objects;

import org.junit.Test;

import static org.junit.Assert.*;

public class reviewTest {

    @Test
    public void Review_instanciates_True(){
        review testreview = new review(001,"sam","great work ethic", 5);
        assertEquals(true,testreview instanceof review);
    }
    @Test
    public void review_get_businessId(){
        review testReview = new review(001,"sam","great work ethic", 5);
        assertEquals(001,testReview.getBusinessId());
    }
    @Test
    public void review_get_review(){
        review testReview = new review(001,"sam","great work ethic", 5);
        assertEquals("Has great service",testReview.getReview());
    }
    @Test
    public void review_get_rating(){
        review testReview = new review(001,"sam","great work ethic", 5);
        assertEquals(9,testReview.getRating());
    }

}