import javax.persistence.*;
import java.util.Date;

@Entity
@TableGenerator(name = "LinkedPurchaseList")
public class LinkedPurchaseList {
    @EmbeddedId
    private LinkedPurchaseListKey id;
    @Column(name = "student_name")
    private String studentName;
    @Column(name = "course_name")
    private String courseName;
    private int price;
    @Column(name = "subscription_date")
    private Date subscriptionDate;

    public LinkedPurchaseListKey getId() {
        return id;
    }

    public void setId(LinkedPurchaseListKey id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }
}
