import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Purchaselist")
public class ShoppingList  {
    @EmbeddedId
    private ShopingListKey key;
    private int price;
    @Column(name = "subscription_date")
    private Date subscriptionDate;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_name",referencedColumnName = "name",insertable = false, updatable = false,nullable = false)
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name ="course_name",referencedColumnName = "name",insertable = false, updatable = false,nullable = false)
    private Course course;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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

    public ShopingListKey getKey() {
        return key;
    }

    public void setKey(ShopingListKey key) {
        this.key = key;
    }
}
