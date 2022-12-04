import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ShopingListKey implements Serializable {
    @Column(name = "student_name")
    private String StudentName;
    @Column(name = "course_name")
    private String CourseName;

    public ShopingListKey() {
    }

    public ShopingListKey(String studentName, String courseName) {
        StudentName = studentName;
        CourseName = courseName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopingListKey that = (ShopingListKey) o;
        return StudentName.equals(that.StudentName) && CourseName.equals(that.CourseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(StudentName, CourseName);
    }
}
