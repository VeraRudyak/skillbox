import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImageProcessor {
    public static void imageProcessor(String sourceDirectory, String destinationDirectory) throws IOException {
        Document doc = Jsoup.connect(sourceDirectory).get();
        Elements elements = doc.select("img");
        BufferedImage image ;
        for (Element element : elements){
            String linksToImages = element.attr("src");
            image = ImageIO.read(new URL(linksToImages));
            if(linksToImages.contains("https") && image != null) {
                ImageIO.write(image, "jpg", new File(destinationDirectory));
                System.out.println(linksToImages);
            }else {
                break;}
        }
    }
}
