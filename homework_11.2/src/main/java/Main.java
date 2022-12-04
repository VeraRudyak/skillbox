
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Введите путь к копируемой папке:");
            Scanner scanner = new Scanner(System.in);
            String filePath = scanner.nextLine(); // C:\Курсы\java_home_work\9.1_File
            System.out.println("Введите путь куда копируем:");
            Scanner scanner2 = new Scanner(System.in);
            String filePath2 = scanner2.nextLine(); // C:\Курсы\java_home_work\folder_for_copy
            FileUtils.copyFolder(filePath, filePath2);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
