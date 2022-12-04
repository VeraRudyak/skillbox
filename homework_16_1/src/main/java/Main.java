import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String password = "mol987N";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("\n" +
                    "SELECT course_name, COUNT(subscription_date) / " +
                    "(TIMESTAMPDIFF(MONTH, MIN(Purchaselist.subscription_date), " +
                    "MAX(Purchaselist.subscription_date)) + 1) " +
                    "AS avg_month_purchases FROM Purchaselist " +
                    "GROUP BY course_name ORDER BY avg_month_purchases DESC;");

            while (resultSet.next()) {
                String courseName = resultSet.getString("course_name");
                String avgPurchases = resultSet.getString("avg_month_purchases");
                System.out.println( courseName + " - " + avgPurchases );
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
