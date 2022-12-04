
import java.io.IOException;
import java.nio.file.Paths;

public class Main {

    public static String directory = String.valueOf(Paths.get("C:/skillbox/java_basics/DocParser/images"));


    public static void main(String[] args) throws IOException {
        ImageProcessor.imageProcessor("https://lenta.ru", directory);
    }

}
