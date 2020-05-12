package objects;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class review implements Comparable<review> {
    private int id;
    private String writtenBy;
    private int businessId;
    private String review;
    private int rating;
    private long createdat;
    private String formattedCreatedAt;

    public review(int businessId, String writtenBy, String review, int rating) {
        this.writtenBy = writtenBy;
        this.businessId = businessId;
        this.review = review;
        this.rating = rating;
        this.createdat = System.currentTimeMillis();
        setFormattedCreatedAt();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public long getCreatedat() {
        return createdat;
    }

    public void setCreatedat() {
        this.createdat = System.currentTimeMillis();
    }

    public String getFormattedCreatedAt(){
        Date date = new Date(createdat);
        String datePatternToUse = "MM/dd/yyyy @ K:mm a"; //see https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
        SimpleDateFormat sdf = new SimpleDateFormat(datePatternToUse);
        return sdf.format(date);
    }

    public void setFormattedCreatedAt(){
        Date date = new Date(this.createdat);
        String datePatternToUse = "MM/dd/yyyy @ K:mm a";
        SimpleDateFormat sdf = new SimpleDateFormat(datePatternToUse);
        this.formattedCreatedAt = sdf.format(date);
    }

    @Override
    public int compareTo(review reviewObject) {
        if (this.createdat < reviewObject.createdat)
        {
            return -1; //this object was made earlier than the second object.
        }
        else if (this.createdat > reviewObject.createdat){ //this object was made later than the second object
            return 1;
        }
        else {
            return 0; //they were made at the same time, which is very unlikely, but mathematically not impossible.
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof review)) return false;
        review review = (review) o;
        return rating == review.rating &&
                id == review.id &&
                businessId == review.businessId &&
                Objects.equals(review, review.review) &&
                Objects.equals(writtenBy, review.writtenBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(review, writtenBy, rating, id, businessId);
    }
}
